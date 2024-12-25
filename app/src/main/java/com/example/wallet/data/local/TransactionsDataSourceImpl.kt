package com.example.wallet.data.local

import com.example.wallet.data.local.db.TransactionsDao
import com.example.wallet.data.local.db.entity.TransactionDto
import com.example.wallet.domain.TransactionsDataSource
import java.math.BigDecimal
import java.time.LocalDateTime
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TransactionsDataSourceImpl @Inject constructor(private val transactionsDao: TransactionsDao) :
    TransactionsDataSource {

    override fun getBalance(): BigDecimal {
        return transactionsDao.getBalance() ?: BigDecimal.ZERO
    }

    override fun topUpBalance(value: BigDecimal) {
        val newBalance = getBalance().plus(value)
        transactionsDao.insertTransaction(
            TransactionDto(
                date = LocalDateTime.now(),
                value = value,
                category = null,
                balanceAfter = newBalance
            )
        )
    }
}