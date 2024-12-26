package com.example.wallet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wallet.domain.GetBalanceUseCase
import com.example.wallet.domain.TopUpBalanceUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class WalletViewModel @Inject constructor(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val topUpBalanceUseCase: TopUpBalanceUseCase
) : ViewModel() {
    private val _balance: MutableLiveData<BigDecimal> = MutableLiveData()
    val balance: LiveData<BigDecimal> get() = _balance

    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            _balance.postValue(getBalanceUseCase())
        }
    }

    fun topUpBalance(value: BigDecimal) {
        viewModelScope.launch(Dispatchers.IO) {
            topUpBalanceUseCase(value)
            getBalance()
        }
    }

    class Factory(
        private val getBalanceUseCase: GetBalanceUseCase,
        private val topUpBalanceUseCase: TopUpBalanceUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WalletViewModel(getBalanceUseCase, topUpBalanceUseCase) as T
        }
    }

}