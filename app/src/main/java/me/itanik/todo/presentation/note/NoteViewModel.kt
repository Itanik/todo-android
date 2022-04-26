package me.itanik.todo.presentation.note

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import me.itanik.todo.data.model.Event
import me.itanik.todo.data.model.Note
import me.itanik.todo.data.repository.NotesRepository
import java.util.*
import javax.inject.Inject

class NoteViewModel @Inject constructor(
    private val notesRepository: NotesRepository
) : ViewModel() {
    private var estimationDate = EstimationDate()
    private val _savingResult: MutableStateFlow<Event> = MutableStateFlow(Event.Idle)
    val savingResult: StateFlow<Event>
        get() = _savingResult

    fun onEstDateSet(year: Int, month: Int, day: Int) {
        estimationDate = estimationDate.copy(
            year = year,
            month = month,
            day = day
        )
    }

    fun onEstTimeSet(hour: Int, minute: Int) {
        estimationDate = estimationDate.copy(
            hour = hour,
            minute = minute
        )
    }

    fun saveNote(title: String, details: String) {
        _savingResult.value = Event.InProgress

        val note = Note(
            UUID.randomUUID(),
            title,
            details,
            Calendar.getInstance().time,
            estimationDate = estimationDate.toDate()
        )
        viewModelScope.launch {
            notesRepository.addNote(note)
            _savingResult.value = Event.Success
            _savingResult.value = Event.Idle
        }
    }
}

data class EstimationDate(
    val year: Int? = null,
    val month: Int? = null,
    val day: Int? = null,
    val hour: Int? = null,
    val minute: Int? = null
)

fun EstimationDate.toDate(): Date? {
    val calendar = if (year != null && month != null && day != null) {
        GregorianCalendar(year, month, day)
    } else {
        if (hour == null && minute == null)
            return null
        GregorianCalendar.getInstance()
    }

    hour?.let { calendar.set(Calendar.HOUR, it) }
    minute?.let { calendar.set(Calendar.MINUTE, it) }

    return calendar.time
}