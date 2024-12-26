package com.example.wallet.domain.entity

import java.math.BigDecimal
import java.time.LocalDate
import java.time.LocalDateTime

sealed class TransactionListItem {
    data class Transaction(
        val value: BigDecimal,
        val date: LocalDateTime,
        val category: TransactionCategory?
    ) : TransactionListItem()

    data class TransactionDate(val date: LocalDate) : TransactionListItem()
}