package com.baptistecarlier.kotlin.datagouvfr.app.adapter

import androidx.recyclerview.widget.DiffUtil
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset

object DatasetCallback: DiffUtil.ItemCallback<Dataset>() {
    override fun areItemsTheSame(oldItem: Dataset, newItem: Dataset) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Dataset, newItem: Dataset) = oldItem == newItem
}