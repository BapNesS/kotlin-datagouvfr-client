package com.baptistecarlier.kotlin.datagouvfr.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.FragmentSettingsBinding
import com.baptistecarlier.kotlin.datagouvfr.app.vm.SettingsViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : Fragment() {

    private val viewModel: SettingsViewModel by viewModels()

    private var _binding: FragmentSettingsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        binding.button.setOnClickListener {
            viewModel.updateTo(binding.edittext.text.toString())
        }
    }

    private fun initObservers() {
        viewModel.apiKey.observe(this.viewLifecycleOwner, {
            binding.edittext.setText(it)
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}