package com.baptistecarlier.kotlin.datagouvfr.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.FragmentDetailsBinding
import com.baptistecarlier.kotlin.datagouvfr.app.vm.DetailsViewModel
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.date
import com.baptistecarlier.kotlin.datagouvfr.extensions.displayName
import com.baptistecarlier.kotlin.datagouvfr.extensions.nullIfEmpty
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.InternalCoroutinesApi

@AndroidEntryPoint
class DetailsFragment : Fragment() {

    private val viewModel: DetailsViewModel by viewModels()

    private var _binding: FragmentDetailsBinding? = null
    private val binding get() = _binding!!

    // Args
    private val datasetId: String?
        get() = arguments?.let { DetailsFragmentArgs.fromBundle(it).datasetId }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
    }

    private fun initObservers() {
        viewModel.data.observe(this.viewLifecycleOwner, {
            if (it != null) {
                binding.loaded(it)
            } else {
                binding.loader()
            }
        })
    }

    private fun FragmentDetailsBinding.loaded(dataset: Dataset) {
        loader.isVisible = false
        loaded.isVisible = true

        title.text = dataset.title
        val authorValue = dataset.organization?.name?.let {
            it
        } ?: run {
            dataset.owner?.displayName()
        }
        this.author.text = getString(R.string.author_formatted, authorValue)
        val tagsValue = dataset.tags?.joinToString(separator = ", ")?.nullIfEmpty()

        setStatsNotNull(id, R.string.id_formatted, dataset.id)
        setStatsNotNull(createDate, R.string.create_date_formatted, dataset.createdAt.date())
        setStatsNotNull(updateDate, R.string.update_date_formatted, dataset.lastUpdate.date())
        setStatsNotNull(tags, R.string.tags_formatted, tagsValue)

        setStats(ressources, R.string.ressources, dataset.communityResources?.size ?: 0)
        setStats(reutilisations, R.string.reutilisations, dataset.metrics?.reuses)
        setStats(favoris, R.string.favoris, dataset.metrics?.followers)

        description.text = dataset.description
    }

    private fun setStatsNotNull(view: TextView, @StringRes label: Int, value: Any?) {
        view.isVisible = (value != null)
        view.text = view.context.getString(label, value)
    }

    private fun setStats(view: TextView, @StringRes label: Int, value: Int? = 0) {
        view.text = view.context.getString(label, value)
    }

    private fun FragmentDetailsBinding.loader() {
        loader.isVisible = true
        loaded.isVisible = false
    }

    @InternalCoroutinesApi
    override fun onStart() {
        super.onStart()
        datasetId?.let {
            viewModel.load(it)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

