package com.andigeeky.finnhub.network.di

import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarNetworkDataSource
import com.andigeeky.finnhub.network.common.NetworkConstants
import com.andigeeky.finnhub.network.ipo.datasource.IPOCalendarRESTDataSource
import com.andigeeky.finnhub.network.ipo.datasource.api.IPOCalendarService
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val ipo_calendar_network_module = module {
    factory { get<Retrofit>().create(IPOCalendarService::class.java) }
    factory<IPOCalendarNetworkDataSource> { IPOCalendarRESTDataSource(get()) }
}

val network_module = module {
    single {
        Retrofit.Builder()
            .baseUrl(NetworkConstants.FINN_HUB_BASE_URL)
            .addConverterFactory(get())
            .build()
    }
    single<Converter.Factory> { GsonConverterFactory.create() }
} + ipo_calendar_network_module