package com.example.wallet.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
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

    private val _isAddEnabled: MutableLiveData<Boolean> = MutableLiveData(false)
    val isAddEnabled: LiveData<Boolean> get() = _isAddEnabled

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData()
    val isLoading: LiveData<Boolean> get() = _isLoading

    fun addTransaction(value: BigDecimal) {
        viewModelScope.launch(Dispatchers.IO) {
            _isLoading.postValue(true)
            doTransactionUseCase.invoke(value, selectedCategory ?: TransactionCategory.GROCERIES)
            _isLoading.postValue(false)
        }
    }

    fun validate(text: String) {
        runCatching { text.toBigDecimal() }.onSuccess { _isAddEnabled.postValue(it != BigDecimal.ZERO) }
            .onFailure { _isAddEnabled.postValue(false) }
    }

    fun selectCategory(category: TransactionCategory) {
        selectedCategory = category
    }

}