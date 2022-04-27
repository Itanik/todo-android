package me.itanik.todo.presentation.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import kotlinx.coroutines.flow.collectLatest
import me.itanik.todo.data.model.Event
import me.itanik.todo.databinding.FragmentNoteBinding
import me.itanik.todo.presentation.base.BaseFragment
import me.itanik.todo.presentation.note.dialogs.DatePickerDialog
import me.itanik.todo.presentation.note.dialogs.TimePickerDialog

/**
 * A fragment for note creation/modification.
 */
class NoteFragment : BaseFragment<FragmentNoteBinding>() {

    private val viewModel by viewModel(NoteViewModel::class.java) { noteViewModelFactory() }
    override val bindingInflater: (LayoutInflater) -> FragmentNoteBinding
        get() = FragmentNoteBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            saveNoteBtn.setOnClickListener {
                val title = titleEditText.text.toString()
                val details = detailsEdittext.text.toString()

                if (title.isBlank()) {
                    Toast.makeText(context, "Title cannot be blank", Toast.LENGTH_SHORT).show()
                    return@setOnClickListener
                }
                saveNoteBtn.isEnabled = false
                viewModel.saveNote(
                    title,
                    details
                )
            }
            selectDateBtn.setOnClickListener {
                showDatePickerDialog()
            }
            selectTimeBtn.setOnClickListener {
                showTimePickerDialog()
            }
        }

        lifecycleScope.launchWhenStarted {
            viewModel.savingResult.collectLatest { event ->
                when (event) {
                    Event.Success -> {
                        Toast.makeText(context, "Note created successfully", Toast.LENGTH_SHORT)
                            .show()
                        findNavController().popBackStack()
                    }
                    else -> {
                        binding.saveNoteBtn.isEnabled = true
                    }
                }
            }
        }
    }

    private fun showDatePickerDialog() {
        DatePickerDialog().apply {
            onDateSet = viewModel::onEstDateSet
        }.show(parentFragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        TimePickerDialog().apply {
            onTimeSet = viewModel::onEstTimeSet
        }.show(parentFragmentManager, "timePicker")
    }
}