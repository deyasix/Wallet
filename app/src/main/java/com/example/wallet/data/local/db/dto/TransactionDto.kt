package com.example.wallet.data.local.db.dto

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.wallet.data.local.db.DatabaseContract
import com.example.wallet.domain.entity.TransactionListItem
import java.math.BigDecimal
import java.time.LocalDateTime

@Entity(tableName = DatabaseContract.Transactions.TABLE_TRANSACTIONS)
data class TransactionDto(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val date: LocalDateTime,
    val value: BigDecimal,
    val category: TransactionCategoryDto?,
    val balanceAfter: BigDecimal
) {
    companion object {
        fun toDomainTransaction(transactionDto: TransactionDto): TransactionListItem.Transaction {
            return TransactionListItem.Transaction(
                transactionDto.id,
                transactionDto.value,
                transactionDto.date,
                transactionDto.category?.let { category ->
                    TransactionCategoryDto.toDomainCategory(category)
                })
        }
    }
}