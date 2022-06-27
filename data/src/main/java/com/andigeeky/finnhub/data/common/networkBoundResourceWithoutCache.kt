package com.andigeeky.finnhub.data.common

import com.andigeeky.finnhub.data.ipo.datasource.NetworkResponse
import com.andigeeky.finnhub.domain.ipo.common.FinnError
import com.andigeeky.finnhub.domain.ipo.common.Resource
import kotlinx.coroutines.flow.flow

internal inline fun <Result> networkBoundResourceWithoutCache(
    crossinline network: suspend () -> NetworkResponse<Result>,
) = flow {
    emit(Resource.Loading())
    val result = when (val response = network()) {
        is NetworkResponse.ClientError -> Resource.Error(FinnError.NetworkError(response.code,
            response.message))
        is NetworkResponse.ServerError -> Resource.Error(FinnError.NetworkError(response.code,
            response.message))
        is NetworkResponse.NoInternet -> Resource.Error(FinnError.NoInternet)
        is NetworkResponse.Success -> Resource.Success(response.response)
    }
    emit(result)
}