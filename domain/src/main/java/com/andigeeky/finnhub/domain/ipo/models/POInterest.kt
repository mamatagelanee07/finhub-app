package com.andigeeky.finnhub.domain.ipo.models

data class POInterest(
    val name: String,
    val location: Location,
)

data class Location(
    val coordinates: LatLong,
    val formattedAddress: List<String>,
)

data class LatLong(
    val lat: Double,
    val long: Double,
)
