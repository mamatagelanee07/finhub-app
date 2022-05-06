package com.andigeeky.finnhub.network.ipo.datasource

import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarNetworkDataSource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendarStatus
import com.andigeeky.finnhub.network.common.retrofitApiCall
import com.andigeeky.finnhub.network.ipo.datasource.api.IPOCalendarService

class IPOCalendarRESTDataSource(private val service: IPOCalendarService) :
    IPOCalendarNetworkDataSource {
    override suspend fun getIPOCalendars() = retrofitApiCall(
        call = { service.getIPOCalendars() },
        parse = {
            it.ipoCalendars.map { remote ->
                IPOCalendar(
                    name = remote.name,
                    date = remote.date,
                    exchange = remote.exchange,
                    numberOfShares = remote.numberOfShares,
                    price = remote.price,
                    symbol = remote.symbol,
                    totalSharesValue = remote.totalSharesValue,
                    status = IPOCalendarStatus.getStatus(remote.status)
                )
            }
        }
    )
}