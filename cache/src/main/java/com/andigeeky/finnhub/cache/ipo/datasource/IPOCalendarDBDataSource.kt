package com.andigeeky.finnhub.cache.ipo.datasource

import com.andigeeky.finnhub.cache.ipo.datasource.database.dao.IPOCalendarDAO
import com.andigeeky.finnhub.cache.ipo.datasource.database.model.IPOCalendarEntity
import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarCacheDataSource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendarStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal class IPOCalendarDBDataSource(
    private val dao: IPOCalendarDAO,
) : IPOCalendarCacheDataSource {
    override suspend fun getIPOCalendars(): Flow<List<IPOCalendar>> {
        return dao.getAll().map { list ->
            list.map {
                IPOCalendar(
                    name = it.name,
                    date = it.date,
                    exchange = it.exchange,
                    numberOfShares = it.numberOfShares,
                    price = it.price,
                    symbol = it.symbol,
                    totalSharesValue = it.totalSharesValue,
                    status = IPOCalendarStatus.getStatus(it.status)
                )
            }
        }
    }

    override suspend fun saveIPOCalendars(calendar: List<IPOCalendar>) {
        dao.deleteAll()
        dao.insertAll(
            calendar.map {
                IPOCalendarEntity(
                    name = it.name,
                    date = it.date,
                    exchange = it.exchange,
                    numberOfShares = it.numberOfShares,
                    price = it.price,
                    symbol = it.symbol,
                    totalSharesValue = it.totalSharesValue,
                    status = it.status.value
                )
            }
        )
    }
}