package me.itanik.todo.presentation

import android.view.LayoutInflater
import me.itanik.todo.databinding.FragmentProfileBinding
import me.itanik.todo.presentation.base.BaseFragment

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentProfileBinding
        get() = FragmentProfileBinding::inflate
}