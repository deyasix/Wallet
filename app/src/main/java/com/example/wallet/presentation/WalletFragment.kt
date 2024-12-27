package com.example.wallet.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.setFragmentResultListener
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.wallet.MainApplication
import com.example.wallet.R
import com.example.wallet.databinding.FragmentWalletBinding
import com.example.wallet.di.GenericViewModelFactory
import com.example.wallet.ext.getCurrencyValue
import com.example.wallet.ext.getFormattedFullDateTime
import com.example.wallet.presentation.TopUpBalanceFragment.Companion.TOP_UP_RESULT_KEY
import com.example.wallet.presentation.TopUpBalanceFragment.Companion.TOP_UP_REQUEST_KEY
import kotlinx.coroutines.launch
import javax.inject.Inject

class WalletFragment : BaseFragment<FragmentWalletBinding>() {

    @Inject
    lateinit var factory: GenericViewModelFactory<WalletViewModel>

    private val transactionsAdapter = TransactionsAdapter()

    private val viewModel: WalletViewModel by viewModels { factory }

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
        setupAdapter()
        setupTopUpFragmentResult()
        viewModel.getBalance()
    }

    private fun setupClickListeners() {
        binding.btnAddTransaction.setOnClickListener {
            findNavController().navigate(R.id.navigate_to_addTransactionFragment)
        }
        binding.btnTopUp.setOnClickListener {
            TopUpBalanceFragment.showDialog(this)
        }
    }

    private fun setupTopUpFragmentResult() {
        setFragmentResultListener(TOP_UP_REQUEST_KEY) { _, bundle ->
            bundle.getString(TOP_UP_RESULT_KEY)?.let {
                viewModel.topUpBalance(it.toBigDecimal())
            }
        }
    }

    private fun setupAdapter() {
        binding.rvTransactions.adapter = transactionsAdapter
    }

    @SuppressLint("SetTextI18n")
    private fun observeState() {
        viewModel.balance.observe(viewLifecycleOwner) {
            binding.tvBalance.text = it.getCurrencyValue()
        }
        viewModel.btcRate.observe(viewLifecycleOwner) {
            binding.tvBtcRate.text = getString(R.string.btc_to_usd, it.rate)
            binding.tvLastUpdated.text = getString(
                R.string.last_updated, it.date.getFormattedFullDateTime()
            )
        }
        viewModel.transactions.observe(viewLifecycleOwner) {
            lifecycleScope.launch {
                transactionsAdapter.submitData(it)
            }
        }
    }

}