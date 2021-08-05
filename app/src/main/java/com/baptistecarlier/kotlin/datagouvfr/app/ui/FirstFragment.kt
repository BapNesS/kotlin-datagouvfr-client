package com.baptistecarlier.kotlin.datagouvfr.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.baptistecarlier.kotlin.datagouvfr.app.R
import com.baptistecarlier.kotlin.datagouvfr.app.repository.Repository
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.app.databinding.FragmentFirstBinding
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment(), CoroutineScope {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            loadSimpleData()
        }
    }

    override val coroutineContext: CoroutineContext = Dispatchers.Main + Job()

    private fun loadSimpleData() {
        launch(Dispatchers.Main) {
            val firstItem = Repository().call()
            displayItem(firstItem)
        }
    }

    private fun displayItem(firstItem: Dataset?) {
        val title = firstItem?.title
        val firstName = firstItem?.owner?.firstName
        val lastName = firstItem?.owner?.lastName
        binding.textviewFirst.text = getString(R.string.display_loaded, title, firstName, lastName)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

