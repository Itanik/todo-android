package me.itanik.todo.presentation.base

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import me.itanik.todo.AppComponentHolder
import me.itanik.todo.di.AppComponent
import me.itanik.todo.di.utils.getViewModel

open class BaseFragment : Fragment() {
    private val appComponent: AppComponent
        get() = (requireActivity().application as AppComponentHolder).appComponent

    /**
     * Provides viewmodel by defined viewmodelfactory in AppComponent.
     */
    protected fun <T : ViewModel, F : ViewModelProvider.Factory> viewModel(
        viewModelClass: Class<T>,
        // refer on {fragment}ViewModelFactory method in AppComponent
        viewModelFactory: AppComponent.() -> F
    ): Lazy<T> = lazy {
        getViewModel(
            appComponent.viewModelFactory(),
            viewModelClass
        )
    }
}