package com.andigeeky.finnhub.domain.ipo.repository

import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import kotlinx.coroutines.flow.Flow

interface IPOCalendarRepository {
    suspend fun getIPOCalendars(): Flow<Resource<List<IPOCalendar>>>
}