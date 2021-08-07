package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.kotlin.datagouvfr.app.repository.Repository
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private val repository = Repository()

    var query: String = ""
        private set

    private val _data = MutableLiveData<List<Dataset>?>()
    val data: LiveData<List<Dataset>?> = _data

    fun seach(query: String) {
        this.query = query
        viewModelScope.launch {
            val resultList = repository.call(query)?.data.orEmpty()
            _data.postValue( resultList )
        }
    }

}
