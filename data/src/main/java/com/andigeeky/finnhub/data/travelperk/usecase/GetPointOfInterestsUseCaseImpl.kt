package com.andigeeky.finnhub.data.travelperk.usecase

import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.LatLong
import com.andigeeky.finnhub.domain.ipo.models.POInterest
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import com.andigeeky.finnhub.domain.travelperk.GetPointOfInterestsUseCase
import com.andigeeky.finnhub.domain.travelperk.repository.PointOfInterestsRepository
import kotlinx.coroutines.flow.Flow

class GetPointOfInterestsUseCaseImpl(private val pointOfInterestsRepository: PointOfInterestsRepository) :
    GetPointOfInterestsUseCase {
    override suspend fun invoke(latLong : LatLong): Flow<Resource<List<POInterest>>> {
       return pointOfInterestsRepository.getPointsOfInterests(latLong)
    }
}