package com.baptistecarlier.kotlin.datagouvfr.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.ItemDatasetBinding
import com.baptistecarlier.kotlin.datagouvfr.app.ui.viewholder.DatasetViewHolder
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset

class DatasetAdapter : PagingDataAdapter<Dataset, DatasetViewHolder>(DatasetCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasetViewHolder {
        val view = ItemDatasetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DatasetViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatasetViewHolder, position: Int) {
        getItem(position)?.let {
            holder.bind(it)
        }
    }

}


