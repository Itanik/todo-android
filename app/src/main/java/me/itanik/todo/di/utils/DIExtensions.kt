package me.itanik.todo.di.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

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