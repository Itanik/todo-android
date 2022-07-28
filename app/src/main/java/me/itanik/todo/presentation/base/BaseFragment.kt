package me.itanik.todo.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import me.itanik.todo.di.AppComponent
import me.itanik.todo.di.utils.appComponent
import me.itanik.todo.di.utils.getViewModel

abstract class BaseFragment<out VB : ViewBinding> : Fragment() {
    private var _binding: VB? = null
    protected val binding: VB
        get() = _binding
            ?: throw Exception("${this.javaClass.simpleName} binding has not been initialized")

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater(inflater)
        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    protected abstract val bindingInflater: (LayoutInflater) -> VB

    /**
     * Provides viewmodel by defined viewmodelfactory in AppComponent.
     */
    protected fun <T : ViewModel, F : ViewModelProvider.Factory> viewModel(
        viewModelClass: Class<T>,
        // refer on {fragment}ViewModelFactory method in AppComponent
        viewModelFactory: AppComponent.() -> F
    ): Lazy<T> = lazy {
        getViewModel(
            requireContext().appComponent.viewModelFactory(),
            viewModelClass
        )
    }
}