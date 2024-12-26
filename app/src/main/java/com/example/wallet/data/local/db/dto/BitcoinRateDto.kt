package com.example.wallet.data.local.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallet.data.local.db.DatabaseContract
import java.time.LocalDateTime

@Entity(tableName = DatabaseContract.BitcoinRates.TABLE_BTC_RATES)
data class BitcoinRateDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDateTime,
    val rate: String,
)