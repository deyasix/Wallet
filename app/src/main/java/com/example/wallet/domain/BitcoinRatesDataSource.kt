package com.example.wallet.domain

import com.example.wallet.domain.entity.BitcoinRate

interface BitcoinRatesDataSource {
    suspend fun getBitcoinRate(): BitcoinRate
}