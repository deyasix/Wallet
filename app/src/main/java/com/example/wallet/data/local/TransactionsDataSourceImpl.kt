package com.example.wallet.data.local

import android.util.Log
import com.example.wallet.data.local.db.TransactionsDao
import com.example.wallet.data.local.db.entity.TransactionCategoryDto
import com.example.wallet.data.local.db.entity.TransactionDto
import com.example.wallet.domain.TransactionsDataSource
import com.example.wallet.domain.entity.TransactionCategory
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

    override fun doTransaction(value: BigDecimal, category: TransactionCategory) {
        val newBalance = getBalance().minus(value)
        transactionsDao.insertTransaction(
            TransactionDto(
                date = LocalDateTime.now(),
                value = value.negate(),
                category = TransactionCategoryDto.getDomainCategory(category),
                balanceAfter = newBalance
            ).also {
                Log.d("TransactionDataSourceImpl", "New transaction: $it")
            }
        )
    }
}