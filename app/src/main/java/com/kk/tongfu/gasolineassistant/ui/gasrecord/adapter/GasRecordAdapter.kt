package com.kk.tongfu.gasolineassistant.ui.gasrecord.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kk.tongfu.gasolineassistant.databinding.AdapterGasRecordBinding
import com.kk.tongfu.gasolineassistant.listener.ItemClickListener
import com.kk.tongfu.gasolineassistant.service.GasRecord

/**
 * Created by tongfu
 * on 2019-11-19
 * Desc:
 */

class GasRecordAdapter(val listener: ItemClickListener<GasRecord>) :
    ListAdapter<GasRecord, GasRecordViewHolder>(GasRecordDiff) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GasRecordViewHolder {
        val adapterGasRecordBinding =
            AdapterGasRecordBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GasRecordViewHolder(adapterGasRecordBinding)
    }

    override fun onBindViewHolder(holder: GasRecordViewHolder, position: Int) {
        val preItem: GasRecord? = if (position-1 >=0) {
            getItem(position -1)
        } else {
            null
        }
        holder.bindItem(
            getItem(position),
            preItem,
            listener
        )
    }

}

class GasRecordViewHolder(private val binding: AdapterGasRecordBinding) :
    RecyclerView.ViewHolder(binding.root) {
    fun bindItem(
        item: GasRecord,
        preItem: GasRecord?,
        listener: ItemClickListener<GasRecord>
    ) {
        binding.item = item
        binding.listener = listener
        binding.items = listOf(item, preItem)
    }


}

object GasRecordDiff : DiffUtil.ItemCallback<GasRecord>() {
    override fun areItemsTheSame(oldItem: GasRecord, newItem: GasRecord): Boolean {
        return oldItem.currentDistance == newItem.currentDistance
    }

    override fun areContentsTheSame(oldItem: GasRecord, newItem: GasRecord): Boolean {
        return oldItem == newItem
    }
}