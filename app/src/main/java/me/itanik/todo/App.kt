package me.itanik.todo

import android.app.Application
import me.itanik.todo.di.AppComponent
import me.itanik.todo.di.DaggerAppComponent
import timber.log.Timber

class App : Application(), AppComponentHolder {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}

interface AppComponentHolder {
    val appComponent: AppComponent
}