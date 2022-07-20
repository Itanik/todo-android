package me.itanik.todo.data.model

sealed class Result<out T> {
    object Initial : Result<Nothing>()
    object Loading : Result<Nothing>()
    data class Success<T>(val data: T) : Result<T>()
    data class Error(val message: String) : Result<Nothing>()
}
