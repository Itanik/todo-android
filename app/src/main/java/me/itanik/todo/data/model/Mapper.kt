package me.itanik.todo.data.model

import me.itanik.todo.data.local.db.entity.NoteEntity

fun Note.toNoteEntity() = NoteEntity(id, title, details, creationDate, editDate, estimationDate)

fun NoteEntity.toNote() = Note(id, title, details, creationDate, editDate, estimationDate)