package com.example.wallet.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.wallet.MainApplication
import com.example.wallet.R
import com.example.wallet.databinding.FragmentWalletBinding
import com.example.wallet.domain.GetBalanceUseCase
import com.example.wallet.domain.TopUpBalanceUseCase
import javax.inject.Inject

class WalletFragment : BaseFragment<FragmentWalletBinding>() {

    @Inject
    lateinit var getBalanceUseCase: GetBalanceUseCase

    @Inject
    lateinit var topUpBalanceUseCase: TopUpBalanceUseCase

    private val viewModel: WalletViewModel by lazy {
        val factory = WalletViewModel.Factory(getBalanceUseCase, topUpBalanceUseCase)
        ViewModelProvider(this, factory)[WalletViewModel::class.java]
    }

    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentWalletBinding
        get() = FragmentWalletBinding::inflate

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? MainApplication)?.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
        observeState()
        viewModel.getBalance()
    }

    private fun setupClickListeners() {
        binding.btnAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_addTransactionFragment)
        }
        binding.btnTopUp.setOnClickListener {
            TopUpBalanceFragment.showDialog(this) {
                viewModel.topUpBalance(it)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun observeState() {
        viewModel.balance.observe(viewLifecycleOwner) {
            binding.tvBalance.text = it.toString()
        }
    }

}