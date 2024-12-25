package com.example.wallet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wallet.domain.GetBalanceUseCase
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class WalletViewModel @Inject constructor(private val getBalanceUseCase: GetBalanceUseCase) :
    ViewModel() {
    private val _balance: MutableLiveData<BigDecimal> = MutableLiveData()
    val balance: LiveData<BigDecimal> get() = _balance

    init {
        getBalance()
    }

    private fun getBalance() {
        viewModelScope.launch {
            _balance.postValue(getBalanceUseCase())
        }
    }

    class Factory constructor(
        private val getBalanceUseCase: GetBalanceUseCase,
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WalletViewModel(getBalanceUseCase) as T
        }
    }

}