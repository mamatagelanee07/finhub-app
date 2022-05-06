package com.andigeeky.finnhub.network.ipo.datasource.api.model

import com.google.gson.annotations.SerializedName

data class IPOCalendarResponse(
    @SerializedName("ipoCalendar")
    val ipoCalendars: List<IPOCalendarRemote>,
)

data class IPOCalendarRemote(
    @SerializedName("name")
    val name: String,
    @SerializedName("date")
    val date: String?,
    @SerializedName("exchange")
    val exchange: String?,
    @SerializedName("numberOfShares")
    val numberOfShares: Long?,
    @SerializedName("price")
    val price: String?,
    @SerializedName("status")
    val status: String,
    @SerializedName("symbol")
    val symbol: String?,
    @SerializedName("totalSharesValue")
    val totalSharesValue: Long?,
)