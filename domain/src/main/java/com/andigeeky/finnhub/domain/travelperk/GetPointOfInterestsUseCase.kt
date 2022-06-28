package com.andigeeky.finnhub.domain.travelperk

import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.LatLong
import com.andigeeky.finnhub.domain.ipo.models.POInterest
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import kotlinx.coroutines.flow.Flow

interface GetPointOfInterestsUseCase {
    suspend operator fun invoke(latLong : LatLong): Flow<Resource<List<POInterest>>>
}