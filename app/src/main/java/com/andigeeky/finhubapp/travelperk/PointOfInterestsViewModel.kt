package com.andigeeky.finhubapp.travelperk

import androidx.lifecycle.viewModelScope
import com.andigeeky.finhubapp.R
import com.andigeeky.finnhub.domain.ipo.common.FinnError
import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.LatLong
import com.andigeeky.finnhub.domain.ipo.models.POInterest
import com.andigeeky.finnhub.domain.travelperk.GetPointOfInterestsUseCase
import io.uniflow.android.AndroidDataFlow
import io.uniflow.core.threading.launchOnIO

class PointOfInterestsViewModel(
    private val useCase: GetPointOfInterestsUseCase,
) : AndroidDataFlow(PointOfInterestsState()) {

    init {
        getPointOfInterests()
    }

    fun reload() {
        getPointOfInterests()
    }

    private fun getPointOfInterests() = viewModelScope.launchOnIO {
        useCase(LatLong(
            lat = 41.403526,
            long = 2.189633
        )).collect { setState(mapState(it)) }
    }

    private fun mapState(it: Resource<List<POInterest>>) =
        when (it) {
            is Resource.Error -> PointOfInterestsState(
                error = it.error?.getErrorMessage(),
                interests = it.data
            )
            is Resource.Loading -> PointOfInterestsState(
                loading = true,
                error = it.error?.getErrorMessage(),
                interests = it.data
            )
            is Resource.Success -> PointOfInterestsState(
                loading = false,
                error = it.error?.getErrorMessage(),
                interests  = it.data
            )
        }
}

private fun FinnError.getErrorMessage() = when (this) {
    FinnError.NoInternet -> R.string.error_no_internet
    is FinnError.NetworkError -> R.string.error_server
    is FinnError.UnknownError -> R.string.error_unknown
}