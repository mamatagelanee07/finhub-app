package com.andigeeky.finhubapp.ipo.ui

import com.andigeeky.finhubapp.R
import com.andigeeky.finhubapp.common.ViewModelTest
import com.andigeeky.finhubapp.ipo.ui.model.IPOUpcomingCalendarState
import com.andigeeky.finnhub.domain.ipo.common.FinnError
import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendarStatus
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import io.mockk.coEvery
import io.mockk.mockk
import io.uniflow.android.test.TestViewObserver
import io.uniflow.android.test.createTestObserver
import kotlinx.coroutines.flow.flowOf
import org.junit.Before
import org.junit.Test

class UpcomingIPOViewModelTest : ViewModelTest() {
    private lateinit var viewModel: UpcomingIPOCalendarViewModel
    private lateinit var useCase: GetUpcomingIPOCalendarUseCase
    private lateinit var view: TestViewObserver

    private val calendars = (1..3).map {
        IPOCalendar(
            name = "name $it",
            price = "price $it",
            symbol = "Symbol $it",
            status = IPOCalendarStatus.values()[it],
            totalSharesValue = it * 100.0,
            numberOfShares = it * 1000.0,
            exchange = "Ex $it",
            date = "$it Jan 2023"
        )
    }

    private val state = IPOUpcomingCalendarState(
        loading = true,
        ipoCalendars = calendars,
        error = R.string.error_no_internet
    )

    @Before
    fun setup() {
        useCase = mockk(relaxed = true)
        viewModel = UpcomingIPOCalendarViewModel(useCase = useCase)
        view = viewModel.createTestObserver()
    }

    @Test
    fun `Given success from cache and network When reload Then should display proper loading & success state`() {
        coEvery { useCase() } returns flowOf(
            Resource.Loading(data = null),
            Resource.Success(data = calendars),
            Resource.Loading(data = calendars),
            Resource.Success(data = calendars)
        )
        viewModel.reload()
        view.verifySequence(
            state.copy(loading = false, ipoCalendars = null, error = null),
            state.copy(loading = true, ipoCalendars = null, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = null),
            state.copy(loading = true, ipoCalendars = calendars, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = null),
        )
    }

    @Test
    fun `Given no network error from network When reload Then should display proper loading & error with cached success state`() {
        coEvery { useCase() } returns flowOf(
            Resource.Loading(data = null),
            Resource.Success(data = calendars),
            Resource.Loading(data = calendars),
            Resource.Error(data = calendars, error = FinnError.NoInternet)
        )
        viewModel.reload()
        view.verifySequence(
            state.copy(loading = false, ipoCalendars = null, error = null),
            state.copy(loading = true, ipoCalendars = null, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = null),
            state.copy(loading = true, ipoCalendars = calendars, error = null),
            state.copy(loading = false,
                ipoCalendars = calendars,
                error = R.string.error_no_internet),
        )
    }

    @Test
    fun `Given server error from network When reload Then should display proper loading & error with cached success state`() {
        coEvery { useCase() } returns flowOf(
            Resource.Loading(data = null),
            Resource.Success(data = calendars),
            Resource.Loading(data = calendars),
            Resource.Error(data = calendars, error = FinnError.UnknownError(Throwable()))
        )
        viewModel.reload()
        view.verifySequence(
            state.copy(loading = false, ipoCalendars = null, error = null),
            state.copy(loading = true, ipoCalendars = null, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = null),
            state.copy(loading = true, ipoCalendars = calendars, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = R.string.error_unknown),
        )
    }

    @Test
    fun `Given network error from network When reload Then should display proper loading & error with cached success state`() {
        coEvery { useCase() } returns flowOf(
            Resource.Loading(data = null),
            Resource.Success(data = calendars),
            Resource.Loading(data = calendars),
            Resource.Error(data = calendars,
                error = FinnError.NetworkError(code = 500, message = "Internal Server Error"))
        )
        viewModel.reload()
        view.verifySequence(
            state.copy(loading = false, ipoCalendars = null, error = null),
            state.copy(loading = true, ipoCalendars = null, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = null),
            state.copy(loading = true, ipoCalendars = calendars, error = null),
            state.copy(loading = false, ipoCalendars = calendars, error = R.string.error_server),
        )
    }
}