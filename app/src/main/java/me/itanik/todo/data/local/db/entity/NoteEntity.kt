package me.itanik.todo.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "notes"
)
data class NoteEntity(
    // note can be received from the server
    @PrimaryKey(autoGenerate = false)
    val id: UUID,
    val title: String,
    val details: String = "",
    @ColumnInfo(name = "creation_date")
    val creationDate: Date,
    @ColumnInfo(name = "edit_date")
    val editDate: Date? = null,
    @ColumnInfo(name = "estimation_date")
    val estimationDate: Date? = null,
)
