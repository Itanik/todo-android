package me.itanik.todo.di.modules

import dagger.Binds
import dagger.Module
import me.itanik.todo.data.repository.NotesRepository
import me.itanik.todo.data.repository.NotesRepositoryImpl

@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindNoteRepository(repository: NotesRepositoryImpl): NotesRepository
}