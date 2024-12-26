package com.example.wallet.data.local.db

object DatabaseContract {
    const val DATABASE_NAME = "wallet_database"

    object Transactions {
        const val TABLE_TRANSACTIONS = "transactions"
    }

    object BitcoinRates {
        const val TABLE_BTC_RATES = "btc_rates"
    }
}