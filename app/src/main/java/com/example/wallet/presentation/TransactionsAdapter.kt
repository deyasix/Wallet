package com.example.wallet.presentation

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.wallet.R
import com.example.wallet.databinding.ItemTransactionBinding
import com.example.wallet.databinding.ItemTransactionDateBinding
import com.example.wallet.domain.entity.TransactionListItem
import com.example.wallet.ext.getFormattedDay
import com.example.wallet.ext.getFormatterForTime
import java.math.BigDecimal

class TransactionsAdapter :
    PagingDataAdapter<TransactionListItem, RecyclerView.ViewHolder>(TransactionsDiffUtil) {

    inner class TransactionViewHolder(private val binding: ItemTransactionBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransactionListItem.Transaction) {
            with(binding) {
                val titleRestId = item.category?.textResId ?: R.string.balance_replenishment
                tvTransactionCategory.text = root.context.getString(titleRestId)
                val isTopUp = item.value > BigDecimal.ZERO
                val valueText = if (isTopUp) "+${item.value}" else item.value.toString()
                tvTransactionValue.text = valueText
                tvTransactionValue.setTextColor(root.context.getColor(if (isTopUp) R.color.green else R.color.red))
                tvDate.text = item.date.format(getFormatterForTime())
            }
        }
    }

    inner class DateSeparatorViewHolder(private val binding: ItemTransactionDateBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: TransactionListItem.TransactionDate) {
            binding.tvDay.text = item.date.getFormattedDay()
        }
    }

    private object TransactionsDiffUtil : DiffUtil.ItemCallback<TransactionListItem>() {
        override fun areItemsTheSame(
            oldItem: TransactionListItem,
            newItem: TransactionListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: TransactionListItem,
            newItem: TransactionListItem
        ): Boolean {
            return oldItem == newItem
        }

        override fun getChangePayload(
            oldItem: TransactionListItem,
            newItem: TransactionListItem
        ): Any {
            return newItem
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        getItem(position)?.let {
            when (it) {
                is TransactionListItem.TransactionDate ->
                    (holder as? DateSeparatorViewHolder)?.bind(it)

                is TransactionListItem.Transaction ->
                    (holder as? TransactionViewHolder)?.bind(it)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == TYPE_DATE_SEPARATOR) DateSeparatorViewHolder(
            ItemTransactionDateBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
        else TransactionViewHolder(
            ItemTransactionBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return if (item is TransactionListItem.TransactionDate) TYPE_DATE_SEPARATOR
        else TYPE_TRANSACTION
    }

    companion object {
        private const val TYPE_DATE_SEPARATOR = 1
        private const val TYPE_TRANSACTION = 2
    }
}