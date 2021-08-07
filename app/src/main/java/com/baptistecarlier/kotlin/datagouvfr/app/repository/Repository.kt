package com.baptistecarlier.kotlin.datagouvfr.app.repository

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrService
import com.baptistecarlier.kotlin.datagouvfr.client.models.DatasetPage

class Repository {

    @Deprecated("To remove")
    private val apiKey = ""

    private val dgfrService = DgfrService(logging = true, apiKey = apiKey)

    suspend fun call(query: String): DatasetPage? = dgfrService.listDatasets(q = query)

}

