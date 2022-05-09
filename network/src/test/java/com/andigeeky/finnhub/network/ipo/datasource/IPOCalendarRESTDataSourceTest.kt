@file:OptIn(ExperimentalCoroutinesApi::class)

package com.andigeeky.finnhub.network.ipo.datasource

import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarNetworkDataSource
import com.andigeeky.finnhub.data.ipo.datasource.NetworkResponse
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendarStatus
import com.andigeeky.finnhub.network.common.TestUtilities
import com.andigeeky.finnhub.network.common.test_network_module
import com.andigeeky.finnhub.network.di.network_module
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import okhttp3.mockwebserver.MockWebServer
import org.junit.*
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.KoinTestRule
import org.koin.test.inject
import retrofit2.mock.MockRetrofit

class IPOCalendarRESTDataSourceTest : KoinTest {
    @get:Rule
    val koinTestRule = KoinTestRule.create {
        modules(network_module + test_network_module)
    }
    private val datasource: IPOCalendarNetworkDataSource by inject()
    private val mockWebServer : MockWebServer by inject()
    private val mockRetrofit : MockRetrofit by inject()

    @Before
    fun setup() {
        mockWebServer.start()
    }

    @Test
    fun `Given success When getIPOCalendars is called Then return list of calendars`() = runTest {
        mockWebServer.enqueue(
            TestUtilities.getMockedHttpResponse("ipo-calendar-success.json", 200)
        )
        val actual = datasource.getIPOCalendars()
        val expected = NetworkResponse.Success(
           (1..5).map {
                IPOCalendar(
                    name = "name $it",
                    date = "2020-04-$it",
                    exchange = "exchange $it",
                    numberOfShares = it * 1000.0,
                    price = "£${it * 10}",
                    symbol = "symbol $it",
                    totalSharesValue =  it * 10000.0,
                    status = IPOCalendarStatus.values()[it-1]
                )
           }
        )
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Given client error When getIPOCalendars is called Then return client error`() = runTest {
        val message = "Resource Not Found"
        mockWebServer.enqueue(
            TestUtilities.getMockedHttpResponse(fileName = "ipo-calendar-error.json", responseCode=400)
        )
        val actual = datasource.getIPOCalendars()
        val expected = NetworkResponse.ClientError<List<IPOCalendar>>(code = 400, message = message)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Given server error When getIPOCalendars is called Then return server error`() = runTest {
        val message = "Resource Not Found"
        mockWebServer.enqueue(
            TestUtilities.getMockedHttpResponse(fileName = "ipo-calendar-error.json", responseCode=500)
        )
        val actual = datasource.getIPOCalendars()
        val expected = NetworkResponse.ServerError<List<IPOCalendar>>(code = 500, message = message)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `Given no internet When getIPOCalendars is called Then return internet error`() = runTest {
        mockRetrofit.networkBehavior().setFailurePercent(100)
       val actual = datasource.getIPOCalendars()
       val expected = NetworkResponse.NoInternet<List<IPOCalendar>>()
        Assert.assertEquals(expected, actual)
    }

    @After
    fun close() {
        mockWebServer.shutdown()
        stopKoin()
    }
}