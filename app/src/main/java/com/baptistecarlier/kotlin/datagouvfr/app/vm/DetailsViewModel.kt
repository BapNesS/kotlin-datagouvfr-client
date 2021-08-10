package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.kotlin.datagouvfr.app.repository.DgfrRepository
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.flow.collect

class DetailsViewModel : ViewModel() {

    private val repository = DgfrRepository(DgfrService())

    private val _data = MutableLiveData<Dataset?>()
    val data: LiveData<Dataset?> = _data

    @InternalCoroutinesApi
    fun load(datasetId: String) {
        viewModelScope.launch {
            repository.getDataset(datasetId).collect {
                _data.postValue( it )
            }
        }
    }

}
