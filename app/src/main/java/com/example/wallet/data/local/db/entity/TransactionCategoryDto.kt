package com.example.wallet.data.local.db.entity

import com.example.wallet.domain.entity.TransactionCategory

enum class TransactionCategoryDto {
    GROCERIES, TAXI, ELECTRONICS, RESTAURANT, OTHER;

    companion object {
        fun getDomainCategory(domainCategory: TransactionCategory): TransactionCategoryDto {
            return when (domainCategory) {
                TransactionCategory.ELECTRONICS -> ELECTRONICS
                TransactionCategory.TAXI -> TAXI
                TransactionCategory.GROCERIES -> GROCERIES
                TransactionCategory.RESTAURANT -> RESTAURANT
                TransactionCategory.OTHER -> OTHER
            }
        }
    }
}