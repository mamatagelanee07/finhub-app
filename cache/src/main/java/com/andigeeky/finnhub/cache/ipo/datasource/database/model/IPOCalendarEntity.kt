package com.andigeeky.finnhub.cache.ipo.datasource.database.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ipo_calendar")
internal data class IPOCalendarEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int=0,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "date") val date: String?,
    @ColumnInfo(name = "exchange") val exchange: String?,
    @ColumnInfo(name = "number_of_shares") val numberOfShares: Long?,
    @ColumnInfo(name = "price") val price: String?,
    @ColumnInfo(name = "status") val status: String?,
    @ColumnInfo(name = "symbol") val symbol: String?,
    @ColumnInfo(name = "total_shares_value") val totalSharesValue: Long?,
)