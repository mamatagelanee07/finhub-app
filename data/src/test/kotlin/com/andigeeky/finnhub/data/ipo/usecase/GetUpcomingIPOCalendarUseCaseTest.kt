@file:OptIn(ExperimentalCoroutinesApi::class)

package com.andigeeky.finnhub.data.ipo.usecase

import app.cash.turbine.test
import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarCacheDataSource
import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarNetworkDataSource
import com.andigeeky.finnhub.data.ipo.repository.IPOCalendarDataRepository
import com.andigeeky.finnhub.domain.ipo.common.Resource
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.repository.IPOCalendarRepository
import com.andigeeky.finnhub.domain.ipo.usecases.GetUpcomingIPOCalendarUseCase
import io.mockk.coEvery
import io.mockk.just
import io.mockk.mockk
import io.mockk.runs
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import java.io.IOException

class GetUpcomingIPOCalendarUseCaseTest {
    private lateinit var cache: IPOCalendarCacheDataSource
    private lateinit var network: IPOCalendarNetworkDataSource
    private lateinit var repository: IPOCalendarRepository
    private lateinit var usecase: GetUpcomingIPOCalendarUseCase
    private val calendars = listOf<IPOCalendar>()

    @Before
    fun setup() {
        cache = mockk()
        network = mockk()
        repository = IPOCalendarDataRepository(cache, network)
        usecase = GetUpcomingIPOCalendarDataUseCaseImpl(repository)
    }

    @Test
    fun `test repository emit loading with data from cache and network`() = runTest {
        coEvery { cache.getIPOCalendars() } returns flowOf(calendars)
        coEvery { network.getIPOCalendars() } returns calendars
        coEvery { cache.saveIPOCalendars(any()) } just runs

        usecase().test {
            val first = awaitItem()
            val second = awaitItem()
            val third = awaitItem()
            assert(first is Resource.Loading && first.data == null)
            assert(second is Resource.Loading && second.data == calendars)
            assert(third is Resource.Success && third.data == calendars)
            awaitComplete()
        }
    }

    @Test
    fun `test repository emit loading with & error data from cache and network consecutively`() =
        runTest {
            coEvery { cache.getIPOCalendars() } returns flowOf(calendars)
            coEvery { network.getIPOCalendars() } throws IOException()
            coEvery { cache.saveIPOCalendars(any()) } just runs

            usecase().test {
                val first = awaitItem()
                val second = awaitItem()
                val third = awaitItem()
                assert(first is Resource.Loading && first.data == null)
                assert(second is Resource.Loading && second.data == calendars)
                assert(third is Resource.Error && third.data == calendars)
                awaitComplete()
            }
        }
}