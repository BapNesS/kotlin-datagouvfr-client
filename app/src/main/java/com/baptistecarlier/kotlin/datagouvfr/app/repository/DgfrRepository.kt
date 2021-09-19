package com.baptistecarlier.kotlin.datagouvfr.app.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.baptistecarlier.kotlin.datagouvfr.app.repository.paging.DatasetPagingSource
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.model.Dataset
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class DgfrRepository(storage: Storage) {

    private var dgfrService = DgfrService()

    private val pageSize = 20

    private val scope = CoroutineScope(Dispatchers.Main + Job())

    init {
        storage.apiKeyFlow.onEach { newKey ->
            dgfrService = DgfrService(apiKey = newKey)
        }.launchIn(scope)
    }

    suspend fun listDatasets(query: String): Flow<PagingData<Dataset>> {
        return Pager(
            config = PagingConfig(pageSize)
        ) {
            DatasetPagingSource(
                dgfrService = dgfrService,
                query = query
            )
        }.flow
    }

    suspend fun getDataset(id: String): Flow<Dataset?> {
        return dgfrService.getDataset(id)
    }

    // Me
    suspend fun me() = dgfrService.me()

}