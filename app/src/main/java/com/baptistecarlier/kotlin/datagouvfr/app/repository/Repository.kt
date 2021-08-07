package com.baptistecarlier.kotlin.datagouvfr.app.repository

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.client.models.DatasetPage
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class Repository {

    @Deprecated("To remove")
    private val apiKey = ""

    private val dgfrService = DgfrService(logging = true, apiKey = apiKey)

    suspend fun call(query: String): Flow<List<Dataset>?> =
        dgfrService.listDatasets(q = query)
            .map {
                it?.data
            }

}

