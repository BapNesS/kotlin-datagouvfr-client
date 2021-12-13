package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class HarvestApiImpl(private val client: HttpClient) : HarvestApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun getHarvestBackends(): Flow<DgfrCallState<HarvestBackend>> = loadingFlow {
        client.get(
            path = "harvest/backends"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun getHarvestJob(
        ident: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<HarvestJobPage>> = loadingFlow {
        client.get(
            path = "harvest/job/$ident/"
        ) {
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun getListHarvesterApi(): Flow<DgfrCallState<List<String>>> = loadingFlow {
        client.get(
            path = "harvest/job_status"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postPreviewHarvestSourceConfig(payload: HarvestSource): Flow<DgfrCallState<HarvestJobPreview>> = loadingFlow {
        client.post(
            path = "harvest/sources/preview"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun deleteHarvestSource(ident: String): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.delete(
            path = "harvest/source/$ident"
        ) {
            addApiKey(apiKey)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getHarvestSource(ident: String): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.get(
            path = "harvest/source/$ident"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateHarvestSource(
        ident: String,
        payload: HarvestSource
    ): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.put(
            path = "harvest/source/$ident"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getListHarvestJobs(
        ident: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<HarvestJob>> = loadingFlow {
        client.get(
            path = "harvest/source/$ident/jobs/"
        )
    }

    override fun getPreviewHarvestSource(ident: String): Flow<DgfrCallState<HarvestJobPreview>> = loadingFlow {
        client.get(
            path = "harvest/source/$ident/preview"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun deleteUnscheduleHarvestSource(ident: String): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.delete(
            path = "harvest/source/$ident/schedule"
        ) {
            addApiKey(apiKey)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postScheduleHarvestSource(
        ident: String,
        payload: String
    ): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.post(
            path = "harvest/source/$ident/schedule"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postValidateHarvestSource(
        ident: String,
        payload: HarvestSourceValidation
    ): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.post(
            path = "harvest/source/$ident/validate"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListHarvestSources(
        page: Int?,
        pageSize: Int?,
        owner: String?,
        deleted: Boolean?
    ): Flow<DgfrCallState<List<HarvestSourcePage>>> = loadingFlow {
        client.get(
            path = "harvest/sources/"
        ) {
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("owner", page)
            parameter("deleted", page)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateHarvestSource(payload: HarvestSource): Flow<DgfrCallState<HarvestSource>> = loadingFlow {
        client.post(
            path = "harvest/sources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }
}
