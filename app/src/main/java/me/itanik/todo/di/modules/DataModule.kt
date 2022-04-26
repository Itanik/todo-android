package me.itanik.todo.di.modules

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import me.itanik.todo.data.local.db.DB_NAME
import me.itanik.todo.data.local.db.NotesDatabase
import me.itanik.todo.data.local.db.dao.NoteDao
import javax.inject.Singleton

@Module
object DataModule {
    @Provides
    @Singleton
    fun provideNotesDatabase(context: Context): NotesDatabase =
        Room.databaseBuilder(context, NotesDatabase::class.java, DB_NAME)
            .build()

    @Provides
    fun provideNoteDao(notesDatabase: NotesDatabase): NoteDao = notesDatabase.noteDao()
}