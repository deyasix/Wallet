package com.example.wallet.domain

import javax.inject.Inject

class TopUpBalanceUseCase @Inject constructor(private val dataSource: TransactionsDataSource) {
    suspend operator fun invoke(value: Int) {
        dataSource.topUpBalance(value)
    }
}