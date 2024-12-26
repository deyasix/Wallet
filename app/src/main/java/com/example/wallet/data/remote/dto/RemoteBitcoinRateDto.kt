package com.example.wallet.data.remote.dto

import com.google.gson.annotations.SerializedName

data class RemoteBitcoinRateDto(
    @SerializedName("bpi")
    val bpi: BpiDto
)