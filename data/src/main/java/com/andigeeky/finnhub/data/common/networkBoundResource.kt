package com.andigeeky.finnhub.data.common

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow

internal inline fun <Result, Request> networkBoundResource(
    crossinline cache: () -> Flow<Result>,
    crossinline network: suspend () -> Request,
    crossinline saveToCache: suspend (Request) -> Unit,
    crossinline shouldFetch: (Result) -> Boolean = { true },
) = flow {
    val data = cache().first()
    val flow = if (shouldFetch(data)) {
        try {
            saveToCache(network())
            cache()
        } catch (throwable: Throwable) {
            cache()
        }
    } else {
        cache()
    }
    emitAll(flow)
}
