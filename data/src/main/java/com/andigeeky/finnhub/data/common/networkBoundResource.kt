package com.andigeeky.finnhub.data.common

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline cache: () -> Flow<ResultType>,
    crossinline network: suspend () -> RequestType,
    crossinline saveToCache: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true },
) = flow {
    val data = cache().first()
    val flow = if (shouldFetch(data)) {
        try {
            saveToCache(network())
            cache().map { it }
        } catch (throwable: Throwable) {
            cache().map { it }
        }
    } else {
        cache().map { it }
    }
    emitAll(flow)
}
