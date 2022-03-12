package me.itanik.todo.presentation.di

import android.content.Context
import dagger.BindsInstance
import dagger.Component
import me.itanik.todo.presentation.di.modules.AppModule
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
}