package com.andigeeky.finnhub.data.ipo.datasource

import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarResult
import kotlinx.coroutines.flow.Flow

interface IPOCalendarCacheDataSource {
    fun getIPOCalendars(): Flow<GetUpcomingIPOCalendarResult>
}

interface IPOCalendarNetworkDataSource {
    suspend fun getIPOCalendars(): GetUpcomingIPOCalendarResult
}
