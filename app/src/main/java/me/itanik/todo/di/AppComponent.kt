package me.itanik.todo.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.itanik.todo.di.modules.AppModule
import me.itanik.todo.di.utils.ViewModelFactory
import me.itanik.todo.presentation.list_of_notes.NoteListViewModel
import me.itanik.todo.presentation.note.NoteViewModel
import javax.inject.Singleton

@Component(
    modules = [
        AppModule::class
    ]
)
@Singleton
interface AppComponent {

    @Component.Factory
    interface Factory {
        fun create(@BindsInstance applicationContext: Context): AppComponent
    }

    fun noteViewModelFactory(): ViewModelFactory<NoteViewModel>
    fun noteListViewModelFactory(): ViewModelFactory<NoteListViewModel>
}