package com.example.wallet.domain.entity

import android.content.Context
import com.example.wallet.R

enum class TransactionCategory(val textResId: Int) {
    GROCERIES(R.string.groceries), TAXI(R.string.taxi), ELECTRONICS(R.string.electronics), RESTAURANT(
        R.string.restaurant
    ),
    OTHER(R.string.other);

    companion object {
        fun getCategoryByText(context: Context, text: String): TransactionCategory? {
            return entries.find { context.getString(it.textResId) == text }
        }
    }
}