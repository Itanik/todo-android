package me.itanik.todo.presentation.note

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.navigation.fragment.findNavController
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
                findNavController().popBackStack()
            }
            selectDateBtn.setOnClickListener {
                showDatePickerDialog()
            }
            selectTimeBtn.setOnClickListener {
                showTimePickerDialog()
            }
        }
    }

    private fun showDatePickerDialog() {
        DatePickerDialog().show(parentFragmentManager, "datePicker")
    }

    private fun showTimePickerDialog() {
        TimePickerDialog().show(parentFragmentManager, "timePicker")
    }
}