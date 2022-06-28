package com.andigeeky.finnhub.data.di

import com.andigeeky.finnhub.data.ipo.repository.IPOCalendarDataRepository
import com.andigeeky.finnhub.data.ipo.usecase.GetUpcomingIPOCalendarDataUseCaseImpl
import com.andigeeky.finnhub.data.travelperk.repository.PointOfInterestsDataRepository
import com.andigeeky.finnhub.data.travelperk.usecase.GetPointOfInterestsUseCaseImpl
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import com.andigeeky.finnhub.domain.travelperk.GetPointOfInterestsUseCase
import com.andigeeky.finnhub.domain.travelperk.repository.PointOfInterestsRepository
import org.koin.dsl.module

private val ipo_data_module= module {
    factory<IPOCalendarRepository> { IPOCalendarDataRepository(get()) }
    factory<GetUpcomingIPOCalendarUseCase> { GetUpcomingIPOCalendarDataUseCaseImpl(get()) }
}

private val travel_perk_data_module = module {
    factory<PointOfInterestsRepository> { PointOfInterestsDataRepository(get()) }
    factory<GetPointOfInterestsUseCase> { GetPointOfInterestsUseCaseImpl(get()) }
}

val data_modules = ipo_data_module + travel_perk_data_module