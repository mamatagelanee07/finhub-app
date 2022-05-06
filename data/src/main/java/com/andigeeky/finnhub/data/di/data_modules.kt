package com.andigeeky.finnhub.data.di

import com.andigeeky.finnhub.data.ipo.repository.IPOCalendarDataRepository
import com.andigeeky.finnhub.data.ipo.usecase.GetUpcomingIPOCalendarDataUseCaseImpl
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import org.koin.dsl.module

private val ipo_data_module= module {
    factory<IPOCalendarRepository> { IPOCalendarDataRepository(get(), get()) }
    factory<GetUpcomingIPOCalendarUseCase> { GetUpcomingIPOCalendarDataUseCaseImpl(get()) }
}

val data_modules = ipo_data_module