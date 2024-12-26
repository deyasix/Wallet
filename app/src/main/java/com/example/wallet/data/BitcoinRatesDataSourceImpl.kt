package com.example.wallet.data

import com.example.wallet.data.local.db.dao.BitcoinRatesDao
import com.example.wallet.data.local.db.dto.BitcoinRateDto
import com.example.wallet.data.remote.BitcoinRateService
import com.example.wallet.domain.BitcoinRatesDataSource
import com.example.wallet.domain.entity.BitcoinRate
import java.time.LocalDateTime
import javax.inject.Inject

class BitcoinRatesDataSourceImpl @Inject constructor(
    private val bitcoinRatesDao: BitcoinRatesDao,
    private val bitcoinRateService: BitcoinRateService
) : BitcoinRatesDataSource {
    override suspend fun getBitcoinRate(): BitcoinRate {
        val savedLastRate = bitcoinRatesDao.getTheLastRate()
        val shouldUpdate = savedLastRate?.let {
            LocalDateTime.now().minusHours(UPDATE_INTERVAL)
                .isAfter(savedLastRate.date)
        } ?: true

        return if (shouldUpdate || savedLastRate == null) {
            val updatedRate = bitcoinRateService.getBtcRate()
            BitcoinRateDto(
                date = LocalDateTime.now(),
                rate = updatedRate.bpi.usd.rate
            ).let { btsDto ->
                bitcoinRatesDao.addBtcRate(btsDto)
                BitcoinRate(btsDto.rate, btsDto.date)
            }
        } else BitcoinRate(savedLastRate.rate, savedLastRate.date)
    }

    companion object {
        private const val UPDATE_INTERVAL = 1L
    }
}