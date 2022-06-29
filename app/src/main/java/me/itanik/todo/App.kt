package me.itanik.todo

import android.app.Application
import android.os.Build
import me.itanik.todo.di.AppComponent
import me.itanik.todo.di.DaggerAppComponent
import timber.log.Timber

class App : Application(), AppComponentProvider {

    override val appComponent: AppComponent by lazy {
        DaggerAppComponent.factory().create(applicationContext)
    }

    override fun onCreate() {
        if (BuildConfig.DEBUG)
            Timber.plant(Timber.DebugTree())
        super.onCreate()
    }
}

interface AppComponentProvider {
    val appComponent: AppComponent
}