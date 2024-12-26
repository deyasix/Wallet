package com.example.wallet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.wallet.domain.DoTransactionUseCase
import com.example.wallet.domain.entity.TransactionCategory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.math.BigDecimal
import javax.inject.Inject

class AddTransactionViewModel @Inject constructor(private val doTransactionUseCase: DoTransactionUseCase) :
    ViewModel() {

    private var selectedCategory: TransactionCategory? = null

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addTransaction(value: BigDecimal) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            doTransactionUseCase.invoke(value, selectedCategory ?: TransactionCategory.GROCERIES)
            _isLoading.postValue(false)
        }
    }

    fun selectCategory(category: TransactionCategory) {
        selectedCategory = category
    }

    class Factory(private val doTransactionUseCase: DoTransactionUseCase) :
        ViewModelProvider.Factory {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {
            return AddTransactionViewModel(doTransactionUseCase) as T
        }
    }

}