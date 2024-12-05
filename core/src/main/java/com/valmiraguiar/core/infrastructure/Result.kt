package com.valmiraguiar.core.infrastructure

sealed class Result<out T> {
    data class Success<out T>(val value: T) : Result<T>()

    abstract class Error(
        open val code: Int? = null,
        open val exception: Exception? = null
    ) : Result<Nothing>()

    data class BackendError(
        override val code: Int? = null,
        override val exception: Exception? = null
    ) : Error()

    data class GenericError(
        override val exception: Exception? = null
    ) : Error()

    object NetworkError : Error()
}

inline fun <T : Any> Result<T>.onSuccess(action: (T) -> Unit): Result<T> {
    if (this is Result.Success) {
        action(this.value)
    }
    return this
}

inline fun <T : Any> Result<T>.onError(action: (Result.Error) -> Unit): Result<T> {
    if (this is Result.Error) {
        action(this)
    }
    return this
}

inline fun <T : Any> Result<T>.onFinally(action: () -> Unit) {
    action()
}