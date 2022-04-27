package me.itanik.todo.data.model

import java.util.*

data class Note(
    val id: UUID,
    val title: String,
    val details: String,
    val creationDate: Date,
    val editDate: Date? = null,
    val estimationDate: Date?
)