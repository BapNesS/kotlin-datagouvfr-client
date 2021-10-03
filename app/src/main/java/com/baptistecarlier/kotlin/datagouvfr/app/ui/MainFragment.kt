package com.baptistecarlier.kotlin.datagouvfr.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.adapter.DatasetAdapter
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.FragmentMainBinding
import com.baptistecarlier.kotlin.datagouvfr.app.vm.MainViewModel
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import com.baptistecarlier.kotlin.datagouvfr.extensions.hideKeyboard
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

@AndroidEntryPoint
class MainFragment : Fragment(), DatasetAdapter.ClickListener {

    private val viewModel: MainViewModel by viewModels()

    private var _binding: FragmentMainBinding? = null
    private val binding get() = _binding!!

    private val adapter = DatasetAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initListeners()
        initObservers()
    }

    private fun initView() {
        val defaultQueryValue = viewModel.query.ifBlank { getString(R.string.default_search_query) }
        with(binding) {
            searchEditText.setText(defaultQueryValue)
            recyclerView.adapter = adapter
        }

    }

    private fun initListeners() {
        with(binding) {
            ctaSearch.setOnClickListener { view ->
                val query = searchEditText.text.toString()
                if (query.isNotEmpty()) {
                    snack(R.string.searching)
                    view.hideKeyboard()
                    viewModel.search(query)
                } else {
                    snack(R.string.empty_query)
                }
            }
        }
    }

    private fun initObservers() {
        lifecycleScope.launchWhenCreated {
            viewModel.posts.collectLatest {
                adapter.submitData(it)
            }
        }
    }

    private fun FragmentMainBinding.snack(@StringRes label: Int) {
        Snackbar
            .make(coordinatorLayout, label, Snackbar.LENGTH_SHORT)
            .setAnchorView(searchEditText)
            .show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onDatasetClick(dataset: Dataset) {
        dataset.id?.let { datasetId ->
            val action =
                MainFragmentDirections.actionMainFragmentToDetailsFragment(
                    datasetId = datasetId
                )
            findNavController().navigate(action)
        }
    }
}




