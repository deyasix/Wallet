package com.example.wallet.data.local.db.dto

import com.example.wallet.domain.entity.TransactionCategory

enum class TransactionCategoryDto {
    GROCERIES, TAXI, ELECTRONICS, RESTAURANT, OTHER;

    companion object {
        fun fromDomainCategory(domainCategory: TransactionCategory): TransactionCategoryDto {
            return when (domainCategory) {
                TransactionCategory.ELECTRONICS -> ELECTRONICS
                TransactionCategory.TAXI -> TAXI
                TransactionCategory.GROCERIES -> GROCERIES
                TransactionCategory.RESTAURANT -> RESTAURANT
                TransactionCategory.OTHER -> OTHER
            }
        }

        fun toDomainCategory(dto: TransactionCategoryDto): TransactionCategory {
            return when (dto) {
                ELECTRONICS -> TransactionCategory.ELECTRONICS
                TAXI -> TransactionCategory.TAXI
                GROCERIES -> TransactionCategory.GROCERIES
                RESTAURANT -> TransactionCategory.RESTAURANT
                OTHER -> TransactionCategory.OTHER
            }
        }
    }
}