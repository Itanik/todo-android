package me.itanik.todo.presentation.list_of_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
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
    private val _noteListStateFlow = MutableStateFlow<Result<NoteListState>>(Result.Initial)
    val noteListStateFlow: StateFlow<Result<NoteListState>> get() = _noteListStateFlow

    fun updateNotesList() = viewModelScope.launch {
        _noteListStateFlow.value = Result.Loading
        _noteListStateFlow.value = Result.Success(NoteListState(notesRepository.getNotes()))
    }

    fun removeNote(note: Note) = viewModelScope.launch {
        notesRepository.deleteNote(note)
        updateNotesList()
    }
}