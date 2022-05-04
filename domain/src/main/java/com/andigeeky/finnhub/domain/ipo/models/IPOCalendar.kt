package com.andigeeky.finnhub.domain.ipo.models

data class IPOCalendar(
    val date: String?,
    val exchange: String?,
    val name: String?,
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
    UNKNOWN(null),
}