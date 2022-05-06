package com.andigeeky.finnhub.network.common

import com.andigeeky.finnhub.network.BuildConfig
import com.andigeeky.finnhub.network.common.NetworkConstants.FINN_HUB_TOKEN_HEADER
import okhttp3.Interceptor
import okhttp3.Response

object HeaderInterceptor : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val requestBuilder = originalRequest.newBuilder()
            .header(FINN_HUB_TOKEN_HEADER, BuildConfig.API_TOKEN)
        val request = requestBuilder.build()
        return chain.proceed(request)
    }
}