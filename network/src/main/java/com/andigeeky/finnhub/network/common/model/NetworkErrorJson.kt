package com.andigeeky.finnhub.network.common.model

import com.google.gson.annotations.SerializedName

data class NetworkErrorJson(
    @SerializedName("error")
    val error : String?
)