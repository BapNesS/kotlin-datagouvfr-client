package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.baptistecarlier.kotlin.datagouvfr.app.repository.DgfrRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val dgfrRepository: DgfrRepository
) : ViewModel() {

    var query: String = ""
        private set

    private val innerQuery = MutableLiveData(query)

    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val posts = flowOf(
        clearListCh.receiveAsFlow().map { PagingData.empty() },
        innerQuery.asFlow().flatMapLatest {
            dgfrRepository.listDatasets(query).cachedIn(viewModelScope)
        }
    ).flattenMerge(2)

    init {
        search(query)
    }

    fun search(query: String) {
        this.query = query
        clearListCh.trySend(Unit).isSuccess
        innerQuery.value = query
    }


}


