package me.itanik.todo.presentation.list_of_notes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.itanik.todo.data.model.Note
import me.itanik.todo.data.repository.NotesRepository
import javax.inject.Inject

class NoteListViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    private val _notesStateFlow = MutableStateFlow(listOf<Note>())
    val notesStateFlow: StateFlow<List<Note>>
        get() = _notesStateFlow

    fun updateNotesList() {
        viewModelScope.launch {
            _notesStateFlow.value = notesRepository.getNotes()
        }
    }

}