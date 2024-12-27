package com.example.wallet.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.wallet.MainApplication
import com.example.wallet.R
import com.example.wallet.databinding.FragmentAddTransactionBinding
import com.example.wallet.di.GenericViewModelFactory
import com.example.wallet.domain.entity.TransactionCategory
import com.example.wallet.ext.requestFocusAndOpenIme
import javax.inject.Inject

class AddTransactionFragment : BaseFragment<FragmentAddTransactionBinding>() {
    override val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> FragmentAddTransactionBinding
        get() = FragmentAddTransactionBinding::inflate

    @Inject
    lateinit var factory: GenericViewModelFactory<AddTransactionViewModel>

    private val viewModel: AddTransactionViewModel by viewModels { factory }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (activity?.applicationContext as? MainApplication)?.appComponent?.inject(this)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.edValue.requestFocusAndOpenIme()

        setupClickListeners()
        observeState()
        setupSpinner()
    }

    private fun setupClickListeners() {
        binding.btnAddTransaction.setOnClickListener {
            val value = binding.edValue.text?.toString()?.takeIf { it.isNotEmpty() }?.toBigDecimal()
            value?.let {
                viewModel.addTransaction(value)
            }
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun observeState() {
        viewModel.isLoading.observe(viewLifecycleOwner) {
            if (!it) findNavController().popBackStack()
        }
        viewModel.isAddEnabled.observe(viewLifecycleOwner) {
            binding.btnAddTransaction.isEnabled = it
        }

        binding.edValue.doAfterTextChanged { viewModel.validate(it.toString()) }
    }

    private fun setupSpinner() {
        ArrayAdapter.createFromResource(
            requireContext(),
            R.array.transaction_categories,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerCategory.adapter = adapter
        }
        binding.spinnerCategory.onItemSelectedListener =
            object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    (view as? TextView)?.text?.takeIf { it.isNotEmpty() }?.let { text ->
                        val category =
                            TransactionCategory.getCategoryByText(requireContext(), text.toString())
                        category?.let {
                            viewModel.selectCategory(category)
                        }
                    }
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}

            }
    }

}