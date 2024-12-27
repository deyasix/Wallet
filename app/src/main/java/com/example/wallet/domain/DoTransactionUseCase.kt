package com.example.wallet.domain

import com.example.wallet.domain.entity.TransactionCategory
import java.math.BigDecimal
import javax.inject.Inject

class DoTransactionUseCase @Inject constructor(private val dataSource: TransactionsDataSource) {
    operator fun invoke(value: BigDecimal, category: TransactionCategory) {
        dataSource.doTransaction(value, category)
    }
}