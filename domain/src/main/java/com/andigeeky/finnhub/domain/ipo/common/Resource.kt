package com.andigeeky.finnhub.domain.ipo.common

sealed class Resource<T>(
    val data: T? = null,
    val error: FinnError? = null,
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Loading<T>(data: T? = null) : Resource<T>(data)
    class Error<T>(error: FinnError, data: T? = null) : Resource<T>(data, error)
}

sealed interface FinnError {
    object NoInternet : FinnError
    data class UnknownError(val throwable: Throwable) : FinnError
    data class NetworkError(val code: Int, val message: String? = null) : FinnError
}