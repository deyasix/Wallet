package com.example.wallet.data.local.db.converters

import androidx.room.TypeConverter
import com.example.wallet.data.local.db.dto.TransactionCategoryDto

class TransactionCategoryConverter {
    @TypeConverter
    fun fromTransactionCategory(category: TransactionCategoryDto): String {
        return category.name
    }

    @TypeConverter
    fun toTransactionCategory(name: String): TransactionCategoryDto {
        return TransactionCategoryDto.valueOf(name)
    }
}