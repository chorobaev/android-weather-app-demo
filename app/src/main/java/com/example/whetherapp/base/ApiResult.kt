package com.example.whetherapp.base

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

sealed class ApiResult<out T> {
    data class Success<out M>(val data: M) : ApiResult<M>()
    data class Error(val throwable: Throwable) : ApiResult<Nothing>()
    object Loading : ApiResult<Nothing>()
}

suspend fun <M> apiCall(block: suspend () -> M): ApiResult<M> {
    return try {
        ApiResult.Success(
            withContext(Dispatchers.IO) { block() }
        )
    } catch (ex: Exception) {
        ApiResult.Error(ex)
    }
}