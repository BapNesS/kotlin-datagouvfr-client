package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.kotlin.datagouvfr.app.repository.DgfrRepository
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val dgfrRepository: DgfrRepository
) : ViewModel() {

    private val _data = MutableLiveData<Dataset?>()
    val data: LiveData<Dataset?> = _data

    @InternalCoroutinesApi
    fun load(datasetId: String) {
        viewModelScope.launch(Dispatchers.IO) {
            dgfrRepository.getDataset(datasetId).collect {
                _data.postValue(it)
            }
        }
    }

}
