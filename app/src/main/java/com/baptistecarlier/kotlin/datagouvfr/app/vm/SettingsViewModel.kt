package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.baptistecarlier.kotlin.datagouvfr.app.repository.DgfrRepository
import com.baptistecarlier.kotlin.datagouvfr.app.repository.Storage
import com.baptistecarlier.kotlin.datagouvfr.client.model.Me
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SettingsViewModel @Inject constructor(
    private val storage: Storage,
    private val dgfrRepository: DgfrRepository
) : ViewModel() {

    private val _apiKey = MutableLiveData("")
    val apiKey: LiveData<String> = _apiKey

    private val _user = MutableLiveData<Me?>(null)
    val user: LiveData<Me?> = _user

    init {
        storage.apiKeyFlow.onEach {
            _apiKey.postValue(it)
        }.launchIn(viewModelScope)
    }

    fun updateTo(newValue: String) {
        viewModelScope.launch(Dispatchers.IO) {
            storage.updateApiKey(newValue)
        }
    }

    fun checkMe() {
        viewModelScope.launch (Dispatchers.IO ) {
            dgfrRepository.me().collect {
                _user.postValue( it )
            }
        }
    }

}
