package me.itanik.todo.presentation

import android.view.LayoutInflater
import me.itanik.todo.databinding.FragmentFocusBinding
import me.itanik.todo.presentation.base.BaseFragment

class FocusFragment : BaseFragment<FragmentFocusBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentFocusBinding
        get() = FragmentFocusBinding::inflate
}