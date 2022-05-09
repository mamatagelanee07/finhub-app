package com.andigeeky.finhubapp.ipo.ui

import androidx.lifecycle.viewModelScope
import com.andigeeky.finhubapp.R
import com.andigeeky.finhubapp.ipo.ui.model.IPOUpcomingCalendarState
import com.andigeeky.finnhub.domain.ipo.common.FinnError
import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.threading.launchOnIO

class UpcomingIPOCalendarViewModel(
    private val useCase: GetUpcomingIPOCalendarUseCase,
) : AndroidDataFlow() {

    init {
        getIPOCalendars()
    }

    fun reload() {
        getIPOCalendars()
    }

    private fun getIPOCalendars() = viewModelScope.launchOnIO {
        useCase().collect { setState(mapState(it)) }
    }

    private fun mapState(it: Resource<List<IPOCalendar>>) =
        when (it) {
            is Resource.Error -> IPOUpcomingCalendarState(
                loading = false,
                error = it.error?.getErrorMessage(),
                ipoCalendars = it.data
            )
            is Resource.Loading -> IPOUpcomingCalendarState(
                loading = true,
                error = it.error?.getErrorMessage(),
                ipoCalendars = it.data
            )
            is Resource.Success -> IPOUpcomingCalendarState(
                loading = false,
                error = it.error?.getErrorMessage(),
                ipoCalendars = it.data
            )
        }
}

private fun FinnError.getErrorMessage() = when (this) {
    FinnError.NoInternet -> R.string.error_no_internet
    is FinnError.NetworkError -> R.string.error_server
    is FinnError.UnknownError -> R.string.error_unknown
}