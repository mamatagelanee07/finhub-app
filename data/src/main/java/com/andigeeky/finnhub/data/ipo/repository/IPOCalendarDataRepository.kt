package com.andigeeky.finnhub.data.ipo.repository

import com.andigeeky.finnhub.data.common.networkBoundResource
import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarCacheDataSource
import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarNetworkDataSource
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarResult
import kotlinx.coroutines.flow.Flow

internal class IPOCalendarDataRepository(
    private val cache: IPOCalendarCacheDataSource,
    private val network: IPOCalendarNetworkDataSource,
) : IPOCalendarRepository {
    override suspend fun getIPOCalendars(): Flow<GetUpcomingIPOCalendarResult> {
        return networkBoundResource(
            cache = { cache.getIPOCalendars() },
            network = { network.getIPOCalendars() },
            saveToCache = { cache.getIPOCalendars() }
        )
    }
}