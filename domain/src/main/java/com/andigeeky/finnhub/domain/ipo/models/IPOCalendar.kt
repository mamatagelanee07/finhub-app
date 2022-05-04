package com.andigeeky.finnhub.domain.ipo.models

data class IPOCalendar(
    val name: String,
    val date: String?,
    val exchange: String?,
    val numberOfShares: Long?,
    val price: String?,
    val status: IPOCalendarStatus,
    val symbol: String?,
    val totalSharesValue: Long?,
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