package com.baptistecarlier.kotlin.datagouvfr.app.ui.viewholder

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.adapter.DatasetAdapter
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.ItemDatasetBinding
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.truncatedDescription


class DatasetViewHolder(
    private val viewBinding: ItemDatasetBinding,
    private val clickListener: DatasetAdapter.ClickListener
) :
    RecyclerView.ViewHolder(viewBinding.root) {

    fun bind(dataset: Dataset) {
        with(viewBinding) {
            title.text = dataset.title
            description.text = dataset.truncatedDescription()
            setStats(ressources, R.string.resources, dataset.resources?.size ?: 0)
            setStats(reutilisations, R.string.reuses, dataset.metrics?.reuses)
            setStats(favoris, R.string.followers, dataset.metrics?.followers)
            root.setOnClickListener {
                clickListener.onDatasetClick(dataset)
            }
        }

    }

    private fun setStats(view: TextView, @StringRes label: Int, value: Int? = 0) {
        view.text = view.context.getString(label, value)
    }
}