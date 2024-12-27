package com.example.wallet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.os.bundleOf
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import com.example.wallet.R
import com.example.wallet.databinding.FragmentTopUpBalanceBinding
import com.example.wallet.ext.requestFocusAndOpenIme
import java.math.BigDecimal

class TopUpBalanceFragment : DialogFragment() {

    private val binding: FragmentTopUpBalanceBinding by lazy {
        FragmentTopUpBalanceBinding.inflate(layoutInflater)
    }

    override fun getTheme(): Int {
        return R.style.RoundedCornersDialog
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = binding.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupEditText()
        setupClickListeners()
        observeState()
    }

    private fun setupClickListeners() {
        with(binding) {
            btnCancel.setOnClickListener {
                dismiss()
            }
            btnTopUp.setOnClickListener {
                val value = edValue.text?.toString()?.takeIf { it.isNotEmpty() }?.toBigDecimal()
                value?.let {
                    setFragmentResult(
                        TOP_UP_REQUEST_KEY,
                        bundleOf(TOP_UP_RESULT_KEY to it.toString())
                    )
                    dismiss()
                }
            }
        }
    }

    private fun observeState() {
        with(binding) {
            edValue.doAfterTextChanged {
                runCatching { it.toString().toBigDecimal() }.onSuccess {
                    btnTopUp.isEnabled = it != BigDecimal.ZERO
                }.onFailure { btnTopUp.isEnabled = false }
            }
        }
    }

    private fun setupEditText() {
        dialog?.window?.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE)
        binding.edValue.requestFocusAndOpenIme()
    }

    companion object {
        private const val TAG = "TopUpBalanceFragment"
        const val TOP_UP_REQUEST_KEY = "TOP_UP_REQUEST_KEY"
        const val TOP_UP_RESULT_KEY = "RESULT_KEY"
        fun showDialog(fragment: Fragment) {
            TopUpBalanceFragment().show(fragment.parentFragmentManager, TAG)
        }
    }
}