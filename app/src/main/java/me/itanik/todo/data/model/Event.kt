package me.itanik.todo.data.model

sealed class Event {
    object Idle : Event()
    object InProgress : Event()
    object Success : Event()
    object Error : Event()
}
