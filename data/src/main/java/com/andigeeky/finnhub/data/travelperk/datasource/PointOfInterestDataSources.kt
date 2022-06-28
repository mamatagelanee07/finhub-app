package com.andigeeky.finnhub.data.travelperk.datasource

import com.andigeeky.finnhub.data.ipo.datasource.NetworkResponse
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.LatLong
import com.andigeeky.finnhub.domain.ipo.models.POInterest
import kotlinx.coroutines.flow.Flow

interface PointOfInterestsNetworkDataSource {
    suspend fun getPointOfInterest(latLog : LatLong): NetworkResponse<List<POInterest>>
}