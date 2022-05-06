package com.andigeeky.finnhub.data.ipo.datasource

import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import kotlinx.coroutines.flow.Flow

interface IPOCalendarCacheDataSource {
    suspend fun getIPOCalendars(): Flow<List<IPOCalendar>>
    suspend fun saveIPOCalendars(calendar: List<IPOCalendar>)
}

interface IPOCalendarNetworkDataSource {
    suspend fun getIPOCalendars(): NetworkResponse<List<IPOCalendar>>
}

sealed interface NetworkResponse<T>{
    data class Success<T>(val response : T) : NetworkResponse<T>
    data class NoInternet<T>(val message : String? = null) : NetworkResponse<T>
    data class ClientError<T>(val code : Int, val message : String? = null) : NetworkResponse<T>
    data class ServerError<T>(val code : Int, val message : String? = null) : NetworkResponse<T>
}