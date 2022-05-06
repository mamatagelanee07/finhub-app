package com.andigeeky.finnhub.domain.ipo.usecases

import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import kotlinx.coroutines.flow.Flow

interface GetUpcomingIPOCalendarUseCase {
    suspend operator fun invoke(): Flow<Resource<List<IPOCalendar>>>
}