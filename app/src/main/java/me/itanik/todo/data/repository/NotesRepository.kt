package me.itanik.todo.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import me.itanik.todo.data.local.db.dao.NoteDao
import me.itanik.todo.data.model.Note
import me.itanik.todo.data.model.toNote
import me.itanik.todo.data.model.toNoteEntity
import java.util.*
import javax.inject.Inject

interface NotesRepository {
    fun getNotes(): Flow<List<Note>>

    suspend fun getNoteById(id: UUID): Note

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {

    override fun getNotes(): Flow<List<Note>> {
        return noteDao.getAll().map { list -> list.map { it.toNote() } }
    }

    override suspend fun getNoteById(id: UUID): Note {
        return noteDao.getNoteById(id).toNote()
    }

    override suspend fun addNote(note: Note) {
        noteDao.insert(note.toNoteEntity())
    }

    override suspend fun updateNote(note: Note) {
        noteDao.update(note.toNoteEntity())
    }

    override suspend fun deleteNote(note: Note) {
        noteDao.delete(note.toNoteEntity())
    }

}