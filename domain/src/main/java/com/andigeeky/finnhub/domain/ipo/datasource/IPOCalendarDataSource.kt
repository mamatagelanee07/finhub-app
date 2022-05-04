package com.andigeeky.finnhub.domain.ipo.datasource

import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarResult

interface IPOCalendarDataSource {
    fun getIPOCalendars(): GetUpcomingIPOCalendarResult
}