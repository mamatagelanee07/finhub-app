package com.andigeeky.finhubapp.travelperk

import androidx.annotation.StringRes
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.POInterest
import io.uniflow.core.flow.data.UIState

data class PointOfInterestsState(
    val loading: Boolean = false,
    @StringRes val error : Int? = null,
    val interests : List<POInterest>? = null
) : UIState()