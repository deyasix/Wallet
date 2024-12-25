package com.example.wallet.di

import android.content.Context
import androidx.room.Room
import com.example.wallet.data.local.db.AppDatabase
import com.example.wallet.data.local.db.DatabaseContract
import com.example.wallet.data.local.db.TransactionsDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RoomModule {
    @Singleton
    @Provides
    fun provideDb(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context.applicationContext,
            AppDatabase::class.java,
            DatabaseContract.DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }

    @Singleton
    @Provides
    fun provideTransactionsDao(db: AppDatabase): TransactionsDao {
        return db.getTransactionsDao()
    }
}