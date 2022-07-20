package me.itanik.todo.presentation.list_of_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import me.itanik.todo.data.model.Note
import me.itanik.todo.data.model.Result
import me.itanik.todo.data.repository.NotesRepository
import javax.inject.Inject

data class NoteListState(
    val noteList: List<Note>
)

class NoteListViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    val noteListStateFlow: StateFlow<Result<NoteListState>> =
        notesRepository.getNotes()
            .map { Result.Success(NoteListState(it)) }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.WhileSubscribed(1000),
                initialValue = Result.Initial
            )

    fun removeNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
    }
}