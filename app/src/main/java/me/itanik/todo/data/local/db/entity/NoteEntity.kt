package me.itanik.todo.data.local.db.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(
    tableName = "notes"
)
data class NoteEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val details: String = "",
    @ColumnInfo(name = "creation_date")val creationDate: Date,
    @ColumnInfo(name = "edit_date")val editDate: Date? = null,
    @ColumnInfo(name = "estimation_date")val estimationDate: Date? = null,
)
