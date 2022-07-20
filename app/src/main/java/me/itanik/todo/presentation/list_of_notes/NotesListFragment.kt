package me.itanik.todo.presentation.list_of_notes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.core.view.postDelayed
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import me.itanik.todo.R
import me.itanik.todo.common.ItemSwipeCallback
import me.itanik.todo.data.model.Result
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
    private var observingStateJob: Job? = null
    private val noteListAdapter by lazy {
        NoteListAdapter { clickedNote ->
            val args = Bundle().apply {
                putString(NOTE_ID_ARG_TAG, clickedNote.id.toString())
            }
            findNavController().navigate(R.id.action_NotesListFragment_to_NoteFragment, args)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.addNewNoteBtn.setOnClickListener {
            findNavController().navigate(R.id.action_NotesListFragment_to_NoteFragment)
        }

        initRecyclerView()
        initDataObserving()

        with(binding.swipeRefresh) {
            setOnRefreshListener {
                // there is no updating logic while network data source not configured
                postDelayed(100L) {
                    isRefreshing = false
                }
            }
        }
    }

    private fun initRecyclerView() {
        // item deleting
        binding.recyclerView.adapter = noteListAdapter
        val onItemSwiped = ItemSwipeCallback { position ->
            val note = noteListAdapter.currentList[position]
            viewModel.removeNote(note)
        }
        ItemTouchHelper(onItemSwiped).attachToRecyclerView(binding.recyclerView)
    }

    private fun initDataObserving() {
        observingStateJob = lifecycleScope.launch {
            viewModel.noteListStateFlow
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .collectLatest { result ->
                    when (result) {
                        is Result.Success -> {
                            binding.swipeRefresh.isRefreshing = false
                            noteListAdapter.submitList(result.data.noteList)
                            Timber.d("Submit notes list")
                        }
                        else -> {}
                    }
                }
        }
    }

    override fun onStop() {
        observingStateJob?.cancel()
        super.onStop()
    }
}