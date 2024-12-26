package com.example.wallet.di

import com.example.wallet.data.local.TransactionsDataSourceImpl
import com.example.wallet.data.BitcoinRatesDataSourceImpl
import com.example.wallet.data.local.db.dao.BitcoinRatesDao
import com.example.wallet.data.local.db.dao.TransactionsDao
import com.example.wallet.data.remote.BitcoinRateService
import com.example.wallet.domain.BitcoinRatesDataSource
import com.example.wallet.domain.TransactionsDataSource
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataSourceModule {

    @Provides
    @Singleton
    fun provideTransactionsDataSource(transactionsDao: TransactionsDao): TransactionsDataSource =
        TransactionsDataSourceImpl(transactionsDao)

    @Provides
    @Singleton
    fun provideBitcoinRatesDataSource(
        bitcoinRatesDao: BitcoinRatesDao,
        bitcoinRateService: BitcoinRateService
    ): BitcoinRatesDataSource =
        BitcoinRatesDataSourceImpl(bitcoinRatesDao, bitcoinRateService)
}