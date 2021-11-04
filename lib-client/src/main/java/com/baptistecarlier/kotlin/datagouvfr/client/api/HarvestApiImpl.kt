package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class HarvestApiImpl(private val client: HttpClient) : HarvestApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun getHarvestBackends(): Flow<DgfrResource<HarvestBackend>> = loadingFlow {
        client.get(
            path = "harvest/backends/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun getHarvestJob(
        ident: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<HarvestJobPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "harvest/job/$ident/?${builder.urlEncore()}"
        )
    }

    override fun getListHarvesterApi(): Flow<DgfrResource<List<String>>> = loadingFlow {
        client.get(
            path = "harvest/job_status/"
        )
    }

    override fun postPreviewHarvestSourceConfig(payload: HarvestSource): Flow<DgfrResource<HarvestJobPreview>> = loadingFlow {
        client.post(
            path = "harvest/sources/preview/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteHarvestSource(ident: String): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.delete(
            path = "harvest/source/$ident/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun getHarvestSource(ident: String): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.get(
            path = "harvest/source/$ident/"
        )
    }

    override fun putUpdateHarvestSource(
        ident: String,
        payload: HarvestSource
    ): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.put(
            path = "harvest/source/$ident/"
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
    ): Flow<DgfrResource<HarvestJob>> = loadingFlow {
        client.get(
            path = "harvest/source/$ident/jobs/"
        )
    }

    override fun getPreviewHarvestSource(ident: String): Flow<DgfrResource<HarvestJobPreview>> = loadingFlow {
        client.get(
            path = "harvest/source/$ident/preview/"
        )
    }

    override fun deleteUnscheduleHarvestSource(ident: String): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.delete(
            path = "harvest/source/$ident/schedule"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postScheduleHarvestSource(
        ident: String,
        payload: String
    ): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.post(
            path = "harvest/source/$ident/schedule/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun postValidateHarvestSource(
        ident: String,
        payload: HarvestSourceValidation
    ): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.post(
            path = "harvest/source/$ident/validate/"
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
    ): Flow<DgfrResource<List<HarvestSourcePage>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)
        builder.appendIfNotNull("owner", page)
        builder.appendIfNotNull("deleted", page)

        client.get(
            path = "harvest/sources/?${builder.urlEncore()}"
        )
    }

    override fun postCreateHarvestSource(payload: HarvestSource): Flow<DgfrResource<HarvestSource>> = loadingFlow {
        client.post(
            path = "harvest/sources/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }
}
