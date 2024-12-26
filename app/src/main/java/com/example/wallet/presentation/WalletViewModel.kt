package com.example.wallet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wallet.domain.GetBalanceUseCase
import com.example.wallet.domain.GetBitcoinRateUseCase
import com.example.wallet.domain.TopUpBalanceUseCase
import com.example.wallet.domain.entity.BitcoinRate
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class WalletViewModel @Inject constructor(
    private val getBalanceUseCase: GetBalanceUseCase,
    private val topUpBalanceUseCase: TopUpBalanceUseCase,
    private val getBitcoinRateUseCase: GetBitcoinRateUseCase
) : ViewModel() {
    private val _balance: MutableLiveData<BigDecimal> = MutableLiveData()
    val balance: LiveData<BigDecimal> get() = _balance

    private val _btcRate: MutableLiveData<BitcoinRate> = MutableLiveData()
    val btcRate: LiveData<BitcoinRate> get() = _btcRate

    init {
        getBitcoinRate()
    }

    fun getBalance() {
        viewModelScope.launch(Dispatchers.IO) {
            _balance.postValue(getBalanceUseCase())
        }
    }

    private fun getBitcoinRate() {
        viewModelScope.launch(Dispatchers.IO) {
            _btcRate.postValue(getBitcoinRateUseCase())
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
        private val topUpBalanceUseCase: TopUpBalanceUseCase,
        private val getBitcoinRateUseCase: GetBitcoinRateUseCase
    ) : ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return WalletViewModel(
                getBalanceUseCase,
                topUpBalanceUseCase,
                getBitcoinRateUseCase
            ) as T
        }
    }

}