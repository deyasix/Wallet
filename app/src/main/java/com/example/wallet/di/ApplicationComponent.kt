package com.example.wallet.di

import com.example.wallet.domain.GetBalanceUseCase
import com.example.wallet.presentation.WalletFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component
interface ApplicationComponent {

    fun getBalanceUseCase(): GetBalanceUseCase

    fun inject(walletFragment: WalletFragment)
}