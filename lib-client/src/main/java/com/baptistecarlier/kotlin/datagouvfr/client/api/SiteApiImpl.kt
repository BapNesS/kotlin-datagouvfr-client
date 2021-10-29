package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.readAndClose
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class SiteApiImpl(private val client: HttpClient): SiteApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getActivity(
        page: Int?,
        pageSize: Int?,
        user: String?,
        organization: String?)
    : Flow<DgfrResource<List<ActivityPage>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)
        builder.appendIfNotNull("user", user)
        builder.appendIfNotNull("organization", user)

        client.get(
            path = "activity/?${builder.urlEncore()}"
        )
    }

    override fun getOembed(
        url: String,
        maxWidth: String?,
        maxHeight: String?,
        format: String?)
    : Flow<DgfrResource<Oembed>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("url", url)
        builder.appendIfNotNull("maxwidth", maxWidth)
        builder.appendIfNotNull("maxheight", maxHeight)
        builder.appendIfNotNull("format", format)

        client.get(
            path = "oembed/?${builder.urlEncore()}"
        )
    }

    override fun getOembeds(references: String): Flow<DgfrResource<List<Oembed>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("references", references)

        client.get(
            path = "oembeds/?${builder.urlEncore()}"
        )
    }

    override fun getSite(): Flow<DgfrResource<Site>> = loadingFlow {
        client.get(
            path = "site/"
        )
    }

    override fun getSiteRdfCatalog(): Flow<DgfrResource<String>> = loadingFlow {
         val response = client.get<HttpResponse>(
            path = "site/catalog"
        )
         response.content.readAndClose().orEmpty()
    }

    override fun getSiteRdfCatalogFormat(format: String): Flow<DgfrResource<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "site/catalog.$format"
        )
         response.content.readAndClose().orEmpty()
    }

    override fun getSiteJsonLdContext(): Flow<DgfrResource<String>> = loadingFlow {
         val response = client.get<HttpResponse>(
            path = "site/context.jsonld"
        )
         response.content.readAndClose().orEmpty()
    }

    override fun getSiteDataPortal(format: String): Flow<DgfrResource<String>> = loadingFlow {
         val response = client.get<HttpResponse>(
            path = "site/data.$format"
        )
         response.content.readAndClose().orEmpty()
    }

    override fun getHomeDatasets(): Flow<DgfrResource<List<Dataset>>> = loadingFlow {
        client.get(
            path = "site/home/datasets/"
        )
    }

    override fun putSetHomeDatasets(datasetIds: List<String>): Flow<DgfrResource<List<Dataset>>> = loadingFlow {
        client.put(
            path = "site/home/datasets/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = datasetIds
        }
    }

    override fun getHomeReuses(): Flow<DgfrResource<List<Reuse>>> = loadingFlow {
        client.get(
            path = "site/home/reuses/"
        )
    }

    override fun putSetHomeReuses(reuseIds: List<String>): Flow<DgfrResource<List<Reuse>>> = loadingFlow {
        client.put(
            path = "site/home/reuses/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = reuseIds
        }
    }

    override fun getSuggestTerritory(
        q: String,
        size: Int?)
    : Flow<DgfrResource<List<Territory>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "territory/suggest/?${builder.urlEncore()}"
        )
    }

}