package com.example.wallet.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import com.example.wallet.databinding.FragmentAddTransactionBinding

class AddTransactionFragment : BaseFragment<FragmentAddTransactionBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddTransactionBinding
        get() = FragmentAddTransactionBinding::inflate

}