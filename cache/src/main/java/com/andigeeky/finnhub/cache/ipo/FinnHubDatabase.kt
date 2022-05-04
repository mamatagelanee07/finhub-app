package com.andigeeky.finnhub.cache.ipo

import androidx.room.Database
import androidx.room.RoomDatabase
import com.andigeeky.finnhub.cache.ipo.datasource.database.dao.IPOCalendarDAO
import com.andigeeky.finnhub.cache.ipo.datasource.database.model.IPOCalendarEntity

@Database(entities = [IPOCalendarEntity::class], version = 1)
internal abstract class FinnHubDatabase : RoomDatabase() {
    abstract fun ipoCalendarDAO(): IPOCalendarDAO
}