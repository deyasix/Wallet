package com.example.wallet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import com.example.wallet.databinding.FragmentTopUpBalanceBinding
import java.math.BigDecimal

class TopUpBalanceFragment(private val onTopUp: (value: BigDecimal) -> Unit) : DialogFragment() {

    private val binding: FragmentTopUpBalanceBinding by lazy {
        FragmentTopUpBalanceBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }
            btnTopUp.setOnClickListener {
                val value = edValue.text?.toString()?.takeIf { it.isNotEmpty() }?.toBigDecimal()
                value?.let {
                    onTopUp.invoke(it)
                    dismiss()
                }
            }
        }
    }

    companion object {
        private const val TAG = "TopUpBalanceFragment"
        fun showDialog(fragment: Fragment, onTopUp: (BigDecimal) -> Unit) {
            TopUpBalanceFragment(onTopUp).show(fragment.parentFragmentManager, TAG)
        }
    }
}