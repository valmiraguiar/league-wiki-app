package com.valmiraguiar.core.infrastructure

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

class SafeApiCaller(
    private val backgroundDispatcher: CoroutineDispatcher = Dispatchers.IO
) {
    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher = backgroundDispatcher,
        apiCall: suspend () -> T
    ): Result<T> {
        return withContext(dispatcher) {
            callApi(apiCall)
        }
    }

    protected suspend fun <T> callApi(apiCall: suspend () -> T): Result<T> {
        return try {
            val result = apiCall.invoke()
            onResult(result)
        } catch (exception: Exception) {
            onError(exception)
        }
    }

    protected suspend fun <T> callApi(
        scope: CoroutineScope? = null,
        apiCall: suspend (scope: CoroutineScope?) -> T
    ): Result<T> {
        return try {
            val result = apiCall(scope)
            onResult(result)
        } catch (exception: Exception) {
            onError(exception)
        }
    }

    private fun <T> onResult(result: T): Result<T> {
        if (result is Response<*> && !result.isSuccessful) {
            return onError(Exception("Erro inesperado!"))
        }
        return Result.Success(result)
    }

    private fun <T> onError(exception: Exception): Result<T> {
        return when (exception) {
            is IOException -> {
                Result.NetworkError
            }

            is HttpException -> {
                val code = exception.code()

                Result.BackendError(code, exception)
            }

            else -> {
                Result.GenericError(exception)
            }
        }
    }
}