package me.itanik.todo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.itanik.todo.data.local.db.dao.NoteDao
import me.itanik.todo.data.local.db.entity.NoteEntity

const val DB_NAME = "notes_db"
const val VERSION = 1

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = VERSION,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}