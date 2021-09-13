package com.baptistecarlier.kotlin.datagouvfr.app.ui

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.FragmentSettingsBinding
import com.baptistecarlier.kotlin.datagouvfr.app.vm.SettingsViewModel
import com.baptistecarlier.kotlin.datagouvfr.extensions.displayName
import dagger.hilt.android.AndroidEntryPoint
import android.view.MenuInflater




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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initListeners()
        initObservers()
    }

    private fun initListeners() {
        with(binding) {
            button.setOnClickListener {
                viewModel.updateTo(edittext.text.toString())
            }
            check.setOnClickListener {
                viewModel.checkMe()
            }
        }
    }

    private fun initObservers() {
        viewModel.apiKey.observe(this.viewLifecycleOwner, {
            binding.edittext.setText(it)
        })
        viewModel.user.observe(this.viewLifecycleOwner, { user ->
            if ( user != null ) {
                binding.checkResult.text = getString(R.string.user_formatted, user.displayName(), user.slug, user.website ?: "-")
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}