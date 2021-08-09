package com.baptistecarlier.kotlin.datagouvfr.app.repository

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.baptistecarlier.kotlin.datagouvfr.app.repository.paging.DatasetPagingSource
import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import kotlinx.coroutines.flow.Flow

class Repository(private val dgfrService: DgfrService) {

    private val pageSize = 20

    fun postsOfSubreddit(query: String): Flow<PagingData<Dataset>> {
        return Pager(
            config = PagingConfig(pageSize)
        ) {
            DatasetPagingSource(
                dgfrService = dgfrService,
                query = query
            )
        }.flow
    }
}