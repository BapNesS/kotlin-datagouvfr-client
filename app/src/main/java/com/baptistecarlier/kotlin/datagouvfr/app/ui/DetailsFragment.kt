package com.baptistecarlier.kotlin.datagouvfr.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.platform.ComposeView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.FragmentDetailsBinding
import com.baptistecarlier.kotlin.datagouvfr.app.theme.OdfDefaultTheme
import com.baptistecarlier.kotlin.datagouvfr.app.ui.compose.DetailsView
import com.baptistecarlier.kotlin.datagouvfr.app.vm.DetailsViewModel
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.*
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    // Args
    private val datasetId: String?
        get() = arguments?.let { DetailsFragmentArgs.fromBundle(it).datasetId }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val dgfrCallStateDataset: DgfrCallState<Dataset> by viewModel.data.observeAsState(DgfrCallState.Loading())
                OdfDefaultTheme(isSystemInDarkTheme()) {
                    if (dgfrCallStateDataset is DgfrCallState.Success) {
                        DetailsView((dgfrCallStateDataset as DgfrCallState.Success<Dataset>).data)
                    }
                }
            }
        }
    }

    private fun FragmentDetailsBinding.loaded(dataset: Dataset) {

        title.text = dataset.title
        val tagsValue = dataset.getTagsOrNull()

        setStatsNotNull(id, R.string.id_formatted, dataset.id)
        setStatsNotNull(createDate, R.string.create_date_formatted, dataset.createdAt.date())
        setStatsNotNull(updateDate, R.string.update_date_formatted, dataset.lastUpdate.date())
        setStatsNotNull(tags, R.string.tags_formatted, tagsValue)

        setStats(ressources, R.string.resources, dataset.resources?.size ?: 0)
        setStats(reutilisations, R.string.reuses, dataset.metrics?.reuses)
        setStats(favoris, R.string.followers, dataset.metrics?.followers)

        description.text = dataset.description
    }

    private fun setStatsNotNull(view: TextView, @StringRes label: Int, value: Any?) {
        view.isVisible = value != null
        view.text = view.context.getString(label, value)
    }

    private fun setStats(view: TextView, @StringRes label: Int, value: Int? = 0) {
        view.text = view.context.getString(label, value)
    }

    @InternalCoroutinesApi
    override fun onStart() {
        super.onStart()
        datasetId?.let {
            viewModel.load(it)
        }
    }

}


