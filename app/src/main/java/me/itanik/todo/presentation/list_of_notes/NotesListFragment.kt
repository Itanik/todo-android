package me.itanik.todo.presentation.list_of_notes

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import me.itanik.todo.R
import me.itanik.todo.databinding.FragmentNotesListBinding
import me.itanik.todo.presentation.base.BaseFragment

/**
 * A fragment that contains list of td notes
 */
class NotesListFragment : BaseFragment<FragmentNotesListBinding>() {

    private val viewModel by viewModel(NoteListViewModel::class.java) { noteListViewModelFactory() }
    override val bindingInflater: (LayoutInflater) -> FragmentNotesListBinding
        get() = FragmentNotesListBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewNoteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_NotesListFragment_to_NoteFragment)
        }
    }
}