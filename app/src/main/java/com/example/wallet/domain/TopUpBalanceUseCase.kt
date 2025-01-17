package com.example.wallet.domain

import java.math.BigDecimal
import javax.inject.Inject

class TopUpBalanceUseCase @Inject constructor(private val dataSource: TransactionsDataSource) {
    operator fun invoke(value: BigDecimal) {
        dataSource.topUpBalance(value)
    }
}