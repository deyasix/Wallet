package com.example.wallet.di

import android.content.Context
import com.example.wallet.domain.GetBalanceUseCase
import com.example.wallet.domain.TopUpBalanceUseCase
import com.example.wallet.presentation.AddTransactionFragment
import com.example.wallet.presentation.WalletFragment
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [RoomModule::class, DataSourceModule::class])
interface ApplicationComponent {

    fun getBalanceUseCase(): GetBalanceUseCase
    fun topUpBalanceUseCase(): TopUpBalanceUseCase

    fun inject(walletFragment: WalletFragment)
    fun inject(addTransactionFragment: AddTransactionFragment)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun context(context: Context): Builder

        fun build(): ApplicationComponent
    }
}