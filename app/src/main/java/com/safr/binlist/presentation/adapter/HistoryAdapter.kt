package com.safr.binlist.presentation.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.safr.binlist.R
import com.safr.binlist.databinding.ItemBinBinding
import com.safr.binlist.domain.model.HistoryItem


interface HistoryNumberClickListener {
    fun onItemClick(item: HistoryItem)
}

class BinDiffUtil : DiffUtil.ItemCallback<HistoryItem>() {

    override fun areItemsTheSame(oldItem: HistoryItem, newItem: HistoryItem) =
        oldItem.bin == newItem.bin

    override fun areContentsTheSame(oldItem: HistoryItem, newItem: HistoryItem) =
        oldItem == newItem

}

class HistoryAdapter(val onHisClickListener: HistoryNumberClickListener) :
    ListAdapter<HistoryItem, HistoryAdapter.BinListViewHolder>(BinDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        BinListViewHolder(
            ItemBinBinding.bind(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.item_bin,
                    parent,
                    false
                )
            )
        )

    override fun onBindViewHolder(holder: BinListViewHolder, position: Int) =
        holder.bind(itemPosition = position)

    inner class BinListViewHolder(private val binding: ItemBinBinding) :
        RecyclerView.ViewHolder(binding.root) {

        @SuppressLint("SetTextI18n")
        fun bind(itemPosition: Int) {

            val bin = getItem(itemPosition)

            binding.binN.text = bin.bin
            binding.nameBankir.text = bin.nameBank

            binding.root.setOnClickListener {
                onHisClickListener.onItemClick(item =  bin)
            }
        }
    }

}
