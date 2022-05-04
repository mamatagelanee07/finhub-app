package com.andigeeky.finnhub.domain.ipo.repository

import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarResult
import kotlinx.coroutines.flow.Flow

interface IPOCalendarRepository {
    fun getIPOCalendars(): Flow<GetUpcomingIPOCalendarResult>
}