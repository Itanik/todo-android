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
    private val _savingResult: MutableStateFlow<Event> = MutableStateFlow(Event.Idle)
    val savingResult: StateFlow<Event>
        get() = _savingResult
    private val calendar = NormalCalendar()

    fun saveNote(title: String, details: String, id: String? = null) = viewModelScope.launch {
        _savingResult.value = Event.InProgress

        if (id == null)
            saveNew(title, details)
        else
            saveOld(title, details, id)

        _savingResult.value = Event.Success
        _savingResult.value = Event.Idle
    }

    private suspend fun saveNew(title: String, details: String) {
        val note = Note(
            id = UUID.randomUUID(),
            title = title,
            details = details,
            creationDate = Calendar.getInstance().time,
            estimationDate = calendar.getDayTime()
        )

        notesRepository.addNote(note)
    }

    private suspend fun saveOld(title: String, details: String, id: String) {
        val note = notesRepository.getNoteById(UUID.fromString(id))

        notesRepository.updateNote(
            note.copy(
                title = title,
                details = details,
                editDate = Calendar.getInstance().time,
                estimationDate = calendar.getDayTime(note.estimationDate)
            )
        )
    }

    suspend fun getNote(noteId: String): Note {
        return notesRepository.getNoteById(UUID.fromString(noteId))
    }

    fun onEstDateSet(year: Int, month: Int, day: Int) {
        calendar.setDate(year, month, day)
    }

    fun onEstTimeSet(hour: Int, minute: Int) {
        calendar.setTime(hour, minute)
    }

    /**
     * Normal calendar without shitty exceptions when values overridden
     */
    private class NormalCalendar {
        private var year: Int? = null
        private var month: Int? = null
        private var day: Int? = null
        private var hour: Int? = null
        private var minute: Int? = null

        private val dateEdited: Boolean
            get() = year != null && month != null && day != null
        private val timeEdited: Boolean
            get() = hour != null && minute != null
        private val calendarEdited: Boolean
            get() = dateEdited || timeEdited

        fun setDate(year: Int, month: Int, day: Int) {
            this.year = year
            this.month = month
            this.day = day
        }

        fun setTime(hour: Int, minute: Int) {
            this.hour = hour
            this.minute = minute
        }

        fun getDayTime(): Date? {
            if (!calendarEdited) return null

            val currentCalendar = GregorianCalendar.getInstance()
            return computeDate(currentCalendar)
        }

        fun getDayTime(oldDate: Date?): Date? {
            oldDate ?: return null
            if (!calendarEdited) return oldDate

            val oldCalendar = Calendar.Builder().apply { setInstant(oldDate) }.build()
            return computeDate(oldCalendar)
        }

        private fun computeDate(currentCalendar: Calendar): Date {
            val builder = Calendar.Builder()
            if (dateEdited) {
                builder.setDate(year!!, month!!, day!!)
                if (!timeEdited) {
                    builder.setTimeOfDay(
                        currentCalendar.get(Calendar.HOUR_OF_DAY),
                        currentCalendar.get(Calendar.MINUTE),
                        0
                    )
                }
            }
            if (timeEdited) {
                builder.setTimeOfDay(hour!!, minute!!, 0)
                if (!dateEdited) {
                    builder.setDate(
                        currentCalendar.get(Calendar.YEAR),
                        currentCalendar.get(Calendar.MONTH),
                        currentCalendar.get(Calendar.DAY_OF_MONTH),
                    )
                }
            }
            return builder.build().time
        }
    }
}