package com.example.wallet.domain

import java.math.BigDecimal

interface TransactionsDataSource {
    fun getBalance(): BigDecimal
    fun topUpBalance(value: BigDecimal)
}