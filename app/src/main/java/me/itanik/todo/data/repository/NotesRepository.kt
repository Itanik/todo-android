package me.itanik.todo.data.repository

import me.itanik.todo.data.local.db.dao.NoteDao
import me.itanik.todo.data.model.Note
import me.itanik.todo.data.model.toNote
import me.itanik.todo.data.model.toNoteEntity
import javax.inject.Inject

interface NotesRepository {
    suspend fun getNotes(): List<Note>

    suspend fun addNote(note: Note)

    suspend fun updateNote(note: Note)

    suspend fun deleteNote(note: Note)
}

class NotesRepositoryImpl @Inject constructor(
    private val noteDao: NoteDao
) : NotesRepository {

    override suspend fun getNotes(): List<Note> {
        return noteDao.getAll().map { it.toNote() }
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