package com.baptistecarlier.kotlin.datagouvfr.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.ItemDatasetBinding
import com.baptistecarlier.kotlin.datagouvfr.app.ui.MainFragment
import com.baptistecarlier.kotlin.datagouvfr.app.ui.viewholder.DatasetViewHolder
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset

class DatasetAdapter(private val clickListener: DatasetAdapter.ClickListener) :
    PagingDataAdapter<Dataset, DatasetViewHolder>(DatasetCallback) {

    interface ClickListener {
        fun onDatasetClick(dataset: Dataset)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasetViewHolder {
        val view = ItemDatasetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DatasetViewHolder(view, clickListener)
    }

    override fun onBindViewHolder(holder: DatasetViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}


