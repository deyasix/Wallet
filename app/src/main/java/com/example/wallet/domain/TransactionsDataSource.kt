package com.example.wallet.domain

import com.example.wallet.domain.entity.TransactionCategory
import java.math.BigDecimal

interface TransactionsDataSource {
    fun getBalance(): BigDecimal
    fun topUpBalance(value: BigDecimal)
    fun doTransaction(value: BigDecimal, category: TransactionCategory)
}