package com.example.wallet.data.local.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.wallet.data.local.db.DatabaseContract
import com.example.wallet.data.local.db.dto.TransactionDto
import java.math.BigDecimal

@Dao
interface TransactionsDao {

    @Query("SELECT balanceAfter FROM ${DatabaseContract.Transactions.TABLE_TRANSACTIONS} ORDER BY id DESC LIMIT 1")
    fun getBalance(): BigDecimal?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertTransaction(transactionDto: TransactionDto)

}