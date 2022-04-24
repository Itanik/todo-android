package me.itanik.todo.presentation.note

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.itanik.todo.databinding.FragmentNoteBinding
import me.itanik.todo.presentation.note.dialogs.DatePickerDialog
import me.itanik.todo.presentation.note.dialogs.TimePickerDialog

/**
 * A fragment for note creation/modification.
 */
class NoteFragment : Fragment() {

    private var _binding: FragmentNoteBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNoteBinding.inflate(inflater, container, false)
        return binding.root
    }

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

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}