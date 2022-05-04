package com.andigeeky.finnhub.data.di

import com.andigeeky.finnhub.data.ipo.repository.IPOCalendarDataRepository
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import org.koin.dsl.module

val ipo_data_module= module {
    factory<IPOCalendarRepository> { IPOCalendarDataRepository(get(), get()) }
}

val data_modules = ipo_data_module