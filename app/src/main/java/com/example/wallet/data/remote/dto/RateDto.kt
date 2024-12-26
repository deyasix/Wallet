package com.example.wallet.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RateDto(
    @SerializedName("rate")
    val rate: String
)