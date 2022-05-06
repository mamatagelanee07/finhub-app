package com.andigeeky.finnhub.data.ipo.usecase

import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import kotlinx.coroutines.flow.Flow

class GetUpcomingIPOCalendarDataUseCaseImpl(private val ipoCalendarRepository: IPOCalendarRepository) :
    GetUpcomingIPOCalendarUseCase {
    override suspend fun invoke(): Flow<Resource<List<IPOCalendar>>> {
       return ipoCalendarRepository.getIPOCalendars()
    }
}