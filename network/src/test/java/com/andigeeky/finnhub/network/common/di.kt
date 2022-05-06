package com.andigeeky.finnhub.network.common

import okhttp3.HttpUrl
import okhttp3.mockwebserver.MockWebServer
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.mock.MockRetrofit


val test_network_module = module {
    single { get<MockWebServer>().url("") }
    single { MockWebServer() }
    single { MockRetrofit.Builder(get()).build() }
    single {
        Retrofit.Builder()
            .baseUrl(get<HttpUrl>())
            .addConverterFactory(get())
            .build()
    }
}