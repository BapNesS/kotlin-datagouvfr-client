package com.baptistecarlier.kotlin.datagouvfr.app.ui.viewholder

import android.widget.TextView
import androidx.annotation.StringRes
import androidx.recyclerview.widget.RecyclerView
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.ItemDatasetBinding
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.truncatedDescription

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