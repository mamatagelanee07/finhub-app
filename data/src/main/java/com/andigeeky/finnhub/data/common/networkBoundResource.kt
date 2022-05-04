package com.andigeeky.finnhub.data.common

import com.andigeeky.finnhub.domain.ipo.common.Resource
import kotlinx.coroutines.flow.*

internal inline fun <Result, Request> networkBoundResource(
    crossinline cache: suspend () -> Flow<Result>,
    crossinline network: suspend () -> Request,
    crossinline saveToCache: suspend (Request) -> Unit,
    crossinline shouldFetch: (Result) -> Boolean = { true },
) = flow {
    emit(Resource.Loading())
    val data = cache().first()
    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveToCache(network())
            cache().map { Resource.Success(it) }
        } catch (throwable: Throwable) {
            cache().map { Resource.Error(throwable, it) }
        }
    } else {
        cache().map { Resource.Success(it) }
    }
    emitAll(flow)
}
