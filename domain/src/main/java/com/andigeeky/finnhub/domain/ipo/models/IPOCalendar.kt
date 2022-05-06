package com.andigeeky.finnhub.domain.ipo.models

data class IPOCalendar(
    val name: String,
    val date: String?,
    val exchange: String?,
    val numberOfShares: Double?,
    val price: String?,
    val status: IPOCalendarStatus,
    val symbol: String?,
    val totalSharesValue: Double?,
)

enum class IPOCalendarStatus(val value: String?) {
    EXPECTED("expected"),
    PRICED("priced"),
    WITHDRAWN("withdrawn"),
    FILED("filed"),
    UNKNOWN(null);

    companion object{
        fun getStatus(value: String?) = values().find { it.value == value } ?: UNKNOWN
    }
}