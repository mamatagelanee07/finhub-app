package com.andigeeky.finnhub.network.ipo.datasource.api

import com.andigeeky.finnhub.network.common.NetworkConstants
import com.andigeeky.finnhub.network.ipo.datasource.api.model.IPOCalendarResponse
import retrofit2.Response
import retrofit2.http.GET

interface IPOCalendarService {
    @GET(NetworkConstants.PATH_IPO_CALENDARS)
    suspend fun getIPOCalendars(): Response<IPOCalendarResponse>
}

