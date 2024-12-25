package com.example.wallet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.wallet.R
import com.example.wallet.databinding.FragmentWalletBinding

class WalletFragment: BaseFragment<FragmentWalletBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWalletBinding
        get() = FragmentWalletBinding::inflate

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        binding.btnAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_addTransactionFragment)
        }
    }

}