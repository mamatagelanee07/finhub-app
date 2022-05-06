package com.andigeeky.finhubapp.ipo.ui

import com.andigeeky.finhubapp.R
import com.andigeeky.finhubapp.ipo.ui.model.IPOUpcomingCalendarState
import com.andigeeky.finnhub.domain.ipo.common.FinnError
import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import io.uniflow.android.AndroidDataFlow

class UpcomingIPOCalendarViewModel(
    private val useCase : GetUpcomingIPOCalendarUseCase
) : AndroidDataFlow() {

    init {
        getIPOCalendars()
    }

    fun getIPOCalendars() =  action {
        useCase().collect{
            setState(
                when(it){
                    is Resource.Error -> IPOUpcomingCalendarState(
                        loading = false,
                        error = it.error?.getErrorMessage(),
                        ipoCalendars = it.data.orEmpty()
                    )
                    is Resource.Loading -> IPOUpcomingCalendarState(
                        loading = false,
                        error = it.error?.getErrorMessage(),
                        ipoCalendars = it.data.orEmpty()
                    )
                    is Resource.Success -> IPOUpcomingCalendarState(
                        loading = false,
                        error = it.error?.getErrorMessage(),
                        ipoCalendars = it.data.orEmpty()
                    )
                }
            )
        }
    }
}

private fun FinnError.getErrorMessage() = when(this){
    FinnError.NoInternet -> R.string.app_name
    is FinnError.NetworkError -> R.string.app_name
    is FinnError.UnknownError -> R.string.app_name
}