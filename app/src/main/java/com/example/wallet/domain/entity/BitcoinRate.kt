package com.example.wallet.domain.entity

import java.time.LocalDateTime

data class BitcoinRate(
    val rate: String,
    val date: LocalDateTime
)