package com.example.wallet.domain

import java.math.BigDecimal
import javax.inject.Inject

class GetBalanceUseCase @Inject constructor(private val transactionsDataSource: TransactionsDataSource) {
    operator fun invoke(): BigDecimal {
        return transactionsDataSource.getBalance()
    }
}