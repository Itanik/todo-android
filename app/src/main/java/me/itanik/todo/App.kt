package me.itanik.todo

import android.app.Application
import me.itanik.todo.di.AppComponent
import me.itanik.todo.di.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    private lateinit var injector: AppComponent

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        injector = DaggerAppComponent.factory().create(applicationContext)
        super.onCreate()
    }
}