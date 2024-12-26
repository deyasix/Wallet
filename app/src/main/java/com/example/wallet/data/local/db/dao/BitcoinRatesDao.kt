package com.example.wallet.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallet.data.local.db.DatabaseContract
import com.example.wallet.data.local.db.dto.BitcoinRateDto

@Dao
interface BitcoinRatesDao {

    @Query("SELECT * FROM ${DatabaseContract.BitcoinRates.TABLE_BTC_RATES} ORDER BY id DESC LIMIT 1")
    fun getTheLastRate(): BitcoinRateDto?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addBtcRate(rate: BitcoinRateDto)

}