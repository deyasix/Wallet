package com.example.wallet.domain

import androidx.paging.PagingData
import com.example.wallet.domain.entity.TransactionListItem
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetTransactionsUseCase @Inject constructor(private val dataSource: TransactionsDataSource) {
    operator fun invoke(): Flow<PagingData<TransactionListItem>> {
        return dataSource.getTransactions()
    }
}