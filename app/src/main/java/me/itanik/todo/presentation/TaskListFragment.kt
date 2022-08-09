package me.itanik.todo.presentation

import android.view.LayoutInflater
import me.itanik.todo.databinding.FragmentTaskListBinding
import me.itanik.todo.presentation.base.BaseFragment

class TaskListFragment : BaseFragment<FragmentTaskListBinding>() {
    override val bindingInflater: (LayoutInflater) -> FragmentTaskListBinding
        get() = FragmentTaskListBinding::inflate
}