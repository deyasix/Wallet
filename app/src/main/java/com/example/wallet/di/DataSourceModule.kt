package com.example.wallet.di

import com.example.wallet.data.local.TransactionsDataSourceImpl
import com.example.wallet.data.local.db.TransactionsDao
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
}