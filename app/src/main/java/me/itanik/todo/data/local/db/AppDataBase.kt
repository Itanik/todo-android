package me.itanik.todo.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import me.itanik.todo.data.local.db.dao.NoteDao
import me.itanik.todo.data.local.db.entity.NoteEntity

const val VERSION = 1

@Database(
    entities = [
        NoteEntity::class,
    ],
    version = VERSION,
)
@TypeConverters(Converters::class)
abstract class AppDataBase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}