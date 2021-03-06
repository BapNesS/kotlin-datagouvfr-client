package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.kotlin.datagouvfr.app.repository.DgfrRepository
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val dgfrRepository: DgfrRepository
) : ViewModel() {

    private val _data = MutableLiveData<DgfrCallState<Dataset>>()
    val data: LiveData<DgfrCallState<Dataset>> = _data

    @InternalCoroutinesApi
    fun load(datasetId: String) {
        viewModelScope.launch {
            dgfrRepository
                .getDataset(datasetId)
                .collect {
                    _data.postValue(it)
                }
        }
    }

}
