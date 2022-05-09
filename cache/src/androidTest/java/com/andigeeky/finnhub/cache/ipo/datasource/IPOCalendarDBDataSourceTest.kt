@file:OptIn(ExperimentalCoroutinesApi::class)

package com.andigeeky.finnhub.cache.ipo.datasource

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import app.cash.turbine.test
import com.andigeeky.finnhub.cache.ipo.FinnHubDatabase
import com.andigeeky.finnhub.cache.ipo.datasource.database.dao.IPOCalendarDAO
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendar
import com.andigeeky.finnhub.domain.ipo.models.IPOCalendarStatus
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.asExecutor
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class IPOCalendarDBDataSourceTest {
    private lateinit var db: FinnHubDatabase
    private lateinit var dao: IPOCalendarDAO
    private lateinit var dbDataSource: IPOCalendarDBDataSource

    private val testDispatcher = StandardTestDispatcher()
    private val testScope = TestScope(testDispatcher)

    private val calendars = listOf(
        IPOCalendar(
            name = "ABC",
            date = "hsadg",
            exchange = "fsdf",
            numberOfShares = 3456.0,
            totalSharesValue = 57487.0,
            status = IPOCalendarStatus.EXPECTED,
            symbol = "gdgd",
            price = "ffsjs"
        )
    )

    @Before
    fun setup() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, FinnHubDatabase::class.java)
            .setTransactionExecutor(testDispatcher.asExecutor())
            .setQueryExecutor(testDispatcher.asExecutor()).build()
        dao = db.ipoCalendarDAO()
        dbDataSource = IPOCalendarDBDataSource(dao)
    }

    @Test
    fun testCalendarIsSaved() = testScope.runTest {
        dbDataSource.saveIPOCalendars(calendars)
        dbDataSource.getIPOCalendars().test {
            Assert.assertEquals(awaitItem(), calendars)
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.close()
    }
}