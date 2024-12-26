package com.example.wallet.data.remote.dto

import com.google.gson.annotations.SerializedName

data class BpiDto(
    @SerializedName("USD")
    val usd: RateDto
)