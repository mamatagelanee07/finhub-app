package com.andigeeky.finnhub.domain.ipo.usecases

import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import kotlinx.coroutines.flow.Flow

class GetUpcomingIPOCalendarUseCase(private val ipoCalendarRepository: IPOCalendarRepository) {
    suspend operator fun invoke(): Flow<GetUpcomingIPOCalendarResult>{
        return ipoCalendarRepository.getIPOCalendars()
    }
}

sealed interface GetUpcomingIPOCalendarResult {
    object NoInternet : GetUpcomingIPOCalendarResult
    object ServerError : GetUpcomingIPOCalendarResult
    data class Success(val ipoCalendars: List<IPOCalendar>) : GetUpcomingIPOCalendarResult
}