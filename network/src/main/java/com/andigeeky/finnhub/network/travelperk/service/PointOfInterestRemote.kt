package com.andigeeky.finnhub.network.travelperk.service

import com.google.gson.annotations.SerializedName

data class IPOCalendarResponseMeta(
    @SerializedName("response")
    val response: IPOCalendarResponse,
)

data class IPOCalendarResponse(
    @SerializedName("venues")
    val interests: List<PointOfInterestRemote>,
)

data class PointOfInterestRemote(
    @SerializedName("name")
    val name: String,
    @SerializedName("location")
    val location: LocationRemote,
)

data class LocationRemote(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lng")
    val lng: Double,
    @SerializedName("formattedAddress")
    val formattedAddress : List<String>?
)