package com.andigeeky.finnhub.cache.ipo.datasource.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.andigeeky.finnhub.cache.ipo.datasource.database.model.IPOCalendarEntity
import kotlinx.coroutines.flow.Flow

@Dao
internal interface IPOCalendarDAO {
    @Query("SELECT * FROM ipo_calendar")
    fun getAll(): Flow<List<IPOCalendarEntity>>

    @Insert
    suspend fun insertAll(ipos: List<IPOCalendarEntity>)

    @Query("DELETE FROM ipo_calendar")
    suspend fun deleteAll()
}