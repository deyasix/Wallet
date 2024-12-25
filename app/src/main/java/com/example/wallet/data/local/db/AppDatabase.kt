package com.example.wallet.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.wallet.data.local.db.converters.BigDecimalConverter
import com.example.wallet.data.local.db.converters.LocalDateTimeConverter
import com.example.wallet.data.local.db.converters.TransactionCategoryConverter
import com.example.wallet.data.local.db.entity.TransactionDto

@Database(entities = [TransactionDto::class], version = 1, exportSchema = false)
@TypeConverters(
    BigDecimalConverter::class,
    LocalDateTimeConverter::class,
    TransactionCategoryConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getTransactionsDao(): TransactionsDao
}