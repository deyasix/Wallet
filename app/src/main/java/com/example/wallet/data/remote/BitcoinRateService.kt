package com.example.wallet.data.remote

import com.example.wallet.data.remote.dto.RemoteBitcoinRateDto
import retrofit2.http.GET

interface BitcoinRateService {

    @GET(GET_BTC_RATE)
    suspend fun getBtcRate(): RemoteBitcoinRateDto

    companion object {
        private const val GET_BTC_RATE = "/v1/bpi/currentprice.json"
    }
}