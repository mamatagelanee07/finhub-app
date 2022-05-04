package com.andigeeky.finnhub.cache.di

import androidx.room.Room
import com.andigeeky.finnhub.cache.di.DatabaseConstants.DATABASE_NAME
import com.andigeeky.finnhub.cache.ipo.FinnHubDatabase
import com.andigeeky.finnhub.cache.ipo.datasource.IPOCalendarDBDataSource
import com.andigeeky.finnhub.data.ipo.datasource.IPOCalendarCacheDataSource
import org.koin.dsl.module

val ipo_database_module = module {
    factory { get<FinnHubDatabase>().ipoCalendarDAO() }
    factory<IPOCalendarCacheDataSource> { IPOCalendarDBDataSource(get()) }
}
val database_modules = module {
    single {
        Room.databaseBuilder(
            get(),
            FinnHubDatabase::class.java,
            DATABASE_NAME
        ).build()
    }
} + ipo_database_module

internal object DatabaseConstants {
    const val DATABASE_NAME = "finn-hub-database"
}