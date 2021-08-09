package com.baptistecarlier.kotlin.datagouvfr.app.vm

import androidx.lifecycle.*
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.baptistecarlier.kotlin.datagouvfr.app.repository.Repository
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*

class MainViewModel : ViewModel() {

    var query: String = ""
        private set

    private val innerQuery = MutableLiveData(query)

    private val repository = Repository(DgfrService(logging = true))

    private val clearListCh = Channel<Unit>(Channel.CONFLATED)

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val posts = flowOf(
        clearListCh.receiveAsFlow().map { PagingData.empty() },
        innerQuery.asFlow().flatMapLatest {
            repository.postsOfSubreddit(query).cachedIn(viewModelScope)
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


