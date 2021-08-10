package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.kotlin.datagouvfr.app.repository.Storage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storage: Storage
) : ViewModel() {

    private val _apiKey = MutableLiveData("")
    val apiKey: LiveData<String> = _apiKey

    init {
        storage.apiKeyFlow.onEach {
            _apiKey.postValue( it )
        }.launchIn(viewModelScope)
    }

    fun updateTo(newValue: String) {
        viewModelScope.launch {
            storage.updateApiKey(newValue)
        }
    }

}
