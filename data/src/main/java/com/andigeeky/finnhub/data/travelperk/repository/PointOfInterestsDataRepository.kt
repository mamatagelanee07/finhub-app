package com.andigeeky.finnhub.data.travelperk.repository

import com.andigeeky.finnhub.data.common.networkBoundResourceWithoutCache
import com.andigeeky.finnhub.data.travelperk.datasource.PointOfInterestsNetworkDataSource
import com.andigeeky.finnhub.domain.ipo.models.LatLong
import com.andigeeky.finnhub.domain.travelperk.repository.PointOfInterestsRepository

internal class PointOfInterestsDataRepository(
    private val network: PointOfInterestsNetworkDataSource,
) : PointOfInterestsRepository {

    override suspend fun getPointsOfInterests(latLong: LatLong) = networkBoundResourceWithoutCache(
        network = { network.getPointOfInterest(latLong) }
    )
}