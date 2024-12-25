package com.example.wallet.data.local.db.converters

import androidx.room.TypeConverter
import com.example.wallet.data.local.db.entity.TransactionCategory

class TransactionCategoryConverter {
    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategory): String {
        return category.name
    }

    @TypeConverter
    fun toTransactionCategory(name: String): TransactionCategory {
        return TransactionCategory.valueOf(name)
    }
}