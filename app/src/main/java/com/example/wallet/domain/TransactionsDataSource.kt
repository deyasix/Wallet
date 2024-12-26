package com.example.wallet.domain

import androidx.paging.PagingData
import com.example.wallet.domain.entity.TransactionCategory
import com.example.wallet.domain.entity.TransactionListItem
import kotlinx.coroutines.flow.Flow
import java.math.BigDecimal

interface TransactionsDataSource {
    fun getBalance(): BigDecimal
    fun topUpBalance(value: BigDecimal)
    fun doTransaction(value: BigDecimal, category: TransactionCategory)
    fun getTransactions(): Flow<PagingData<TransactionListItem>>
}