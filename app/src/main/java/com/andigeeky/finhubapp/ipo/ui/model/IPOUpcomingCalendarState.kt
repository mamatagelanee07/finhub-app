package com.andigeeky.finhubapp.ipo.ui.model

import androidx.annotation.StringRes
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import io.uniflow.core.flow.data.UIState

data class IPOUpcomingCalendarState(
    val loading: Boolean = true,
    @StringRes val error : Int? = null,
    val ipoCalendars : List<IPOCalendar>? = null
) : UIState()