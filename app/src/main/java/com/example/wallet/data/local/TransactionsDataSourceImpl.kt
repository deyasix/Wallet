package com.example.wallet.data.local

import android.util.Log
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.insertSeparators
import androidx.paging.map
import com.example.wallet.data.local.db.dao.TransactionsDao
import com.example.wallet.data.local.db.dto.TransactionCategoryDto
import com.example.wallet.data.local.db.dto.TransactionDto
import com.example.wallet.domain.TransactionsDataSource
import com.example.wallet.domain.entity.TransactionCategory
import com.example.wallet.domain.entity.TransactionListItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.math.BigDecimal
import java.time.LocalDateTime
import java.time.ZoneId
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
                category = TransactionCategoryDto.fromDomainCategory(category),
                balanceAfter = newBalance
            ).also {
                Log.d("TransactionDataSourceImpl", "New transaction: $it")
            }
        )
    }

    override fun getTransactions(): Flow<PagingData<TransactionListItem>> {
        return Pager(
            config = PagingConfig(pageSize = PAGE_SIZE, initialLoadSize = PAGE_SIZE),
            pagingSourceFactory = { transactionsDao.getTransactions() }
        ).flow.map {
            it.map { transactionDto ->
                TransactionListItem.Transaction(
                    transactionDto.value,
                    transactionDto.date,
                    transactionDto.category?.let { category ->
                        TransactionCategoryDto.toDomainCategory(category)
                    })
            }
                .insertSeparators { transaction: TransactionListItem.Transaction?, transaction2: TransactionListItem.Transaction? ->
                    val isDifferentDate =
                        transaction?.date?.toLocalDate() != transaction2?.date?.toLocalDate()
                    if (transaction2 != null && isDifferentDate) {
                        TransactionListItem.TransactionDate(
                            transaction2.date.atZone(ZoneId.systemDefault()).toLocalDate()
                        )
                    } else null
                }
        }
    }

    companion object {
        private const val PAGE_SIZE = 20
    }
}