package me.itanik.todo.presentation.list_of_notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.coroutines.flow.collectLatest
import me.itanik.todo.R
import me.itanik.todo.common.ItemSwipeCallback
import me.itanik.todo.databinding.FragmentNotesListBinding
import me.itanik.todo.presentation.base.BaseFragment
import me.itanik.todo.presentation.note.NOTE_ID_ARG_TAG
import timber.log.Timber

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

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val adapter = NoteListAdapter { clickedNote ->
            val args = Bundle().apply {
                putString(NOTE_ID_ARG_TAG, clickedNote.id.toString())
            }
            findNavController().navigate(R.id.action_NotesListFragment_to_NoteFragment, args)
        }

        // item deleting
        binding.recyclerView.adapter = adapter
        val onItemSwiped = ItemSwipeCallback { position ->
            val note = adapter.currentList[position]
            viewModel.removeNote(note)
        }
        ItemTouchHelper(onItemSwiped).attachToRecyclerView(binding.recyclerView)

        // item list updating
        lifecycleScope.launchWhenStarted {
            viewModel.notesStateFlow.collectLatest { noteList ->
                Timber.d("Submit notes list")
                adapter.submitList(noteList)
            }
        }
        viewModel.updateNotesList()
    }
}