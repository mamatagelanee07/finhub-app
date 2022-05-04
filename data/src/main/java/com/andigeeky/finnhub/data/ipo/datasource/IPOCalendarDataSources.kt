package com.andigeeky.finnhub.data.ipo.datasource

import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import kotlinx.coroutines.flow.Flow

interface IPOCalendarCacheDataSource {
    suspend fun getIPOCalendars(): Flow<List<IPOCalendar>>
    suspend fun saveIPOCalendars(getUpcomingIPOCalendarResult: List<IPOCalendar>)
}

interface IPOCalendarNetworkDataSource {
    suspend fun getIPOCalendars(): List<IPOCalendar>
}
