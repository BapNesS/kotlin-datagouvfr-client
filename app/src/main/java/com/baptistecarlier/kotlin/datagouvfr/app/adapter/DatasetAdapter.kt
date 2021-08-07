package com.baptistecarlier.kotlin.datagouvfr.app.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.ItemDatasetBinding
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.truncatedDescription

class DatasetAdapter :
    ListAdapter<Dataset, DatasetAdapter.DatasetViewHolder>(DatasetCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DatasetViewHolder {
        val view = ItemDatasetBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DatasetViewHolder(view)
    }

    override fun onBindViewHolder(holder: DatasetViewHolder, position: Int) {
        val product = getItem(position)
        holder.bind(product)
    }

    class DatasetViewHolder(private val viewBinding: ItemDatasetBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {

        fun bind(item: Dataset) {
            with(viewBinding) {
                title.text = item.title
                description.text = item.truncatedDescription()
                setStats(ressources, R.string.ressources, item.communityResources?.size ?: 0)
                setStats(reutilisations, R.string.reutilisations, item.metrics?.reuses)
                setStats(favoris, R.string.favoris, item.metrics?.followers)
            }
        }

        private fun setStats(view: TextView, @StringRes label: Int, value: Int? = 0) {
            view.text = view.context.getString(label, value)
        }
    }

}

