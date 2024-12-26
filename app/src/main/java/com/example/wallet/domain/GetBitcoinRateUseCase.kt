package com.example.wallet.domain

import com.example.wallet.domain.entity.BitcoinRate
import javax.inject.Inject

class GetBitcoinRateUseCase @Inject constructor(private val bitcoinRatesDataSource: BitcoinRatesDataSource) {

    suspend operator fun invoke(): BitcoinRate {
        return bitcoinRatesDataSource.getBitcoinRate()
    }
}