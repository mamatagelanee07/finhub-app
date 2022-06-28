package com.andigeeky.finnhub.network.travelperk.service

import com.andigeeky.finnhub.network.common.NetworkConstants
import com.andigeeky.finnhub.network.ipo.datasource.api.model.IPOCalendarResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface FourSquareService {
    @GET(NetworkConstants.PATH_VENUE_SEARCH)
    suspend fun getPointOfInterests(
        @Query("ll") latLong: String,
        @Query("client_secret") clientSecret: String,
        @Query("client_id") clientId: String,
        @Query("v") version: String,
    ): Response<IPOCalendarResponseMeta>
}

