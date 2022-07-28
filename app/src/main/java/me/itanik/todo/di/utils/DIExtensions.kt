package me.itanik.todo.di.utils

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.itanik.todo.AppComponentProvider

fun <T : ViewModel> Fragment.getViewModel(
    factory: ViewModelProvider.Factory,
    viewModelClass: Class<T>
): T =
    ViewModelProvider(this, factory)[viewModelClass]

fun <T : ViewModel> AppCompatActivity.getViewModel(
    factory: ViewModelProvider.Factory,
    viewModelClass: Class<T>
): T =
    ViewModelProvider(this, factory)[viewModelClass]

val Context.appComponent
    get() = when (this) {
        is AppComponentProvider -> this.appComponent
        else -> (this.applicationContext as AppComponentProvider).appComponent
    }