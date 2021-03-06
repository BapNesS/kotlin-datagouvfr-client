package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.readAndClose
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class SiteApiImpl(private val client: HttpClient) : SiteApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getActivity(
        page: Int?,
        pageSize: Int?,
        user: String?,
        organization: String?
    ): Flow<DgfrCallState<List<ActivityPage>>> = loadingFlow {
        client.get(
            path = "activity"
        ) {
            parameter("page", page)
            parameter("page_size", pageSize)
            parameter("user", user)
            parameter("organization", user)
        }
    }

    override fun getOembed(
        url: String,
        maxWidth: String?,
        maxHeight: String?,
        format: String?
    ): Flow<DgfrCallState<Oembed>> = loadingFlow {
        client.get(
            path = "oembed"
        ) {
            parameter("url", url)
            parameter("maxwidth", maxWidth)
            parameter("maxheight", maxHeight)
            parameter("format", format)
        }
    }

    override fun getOembeds(references: String): Flow<DgfrCallState<List<Oembed>>> = loadingFlow {
        client.get(
            path = "oembeds/"
        ) {
            parameter("references", references)
        }
    }

    override fun getSite(): Flow<DgfrCallState<Site>> = loadingFlow {
        client.get(
            path = "site/"
        )
    }

    override fun getSiteRdfCatalog(): Flow<DgfrCallState<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "site/catalog"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getSiteRdfCatalogFormat(format: String): Flow<DgfrCallState<String>> =
        loadingFlow {
            val response = client.get<HttpResponse>(
                path = "site/catalog.$format"
            )
            response.content.readAndClose().orEmpty()
        }

    override fun getSiteJsonLdContext(): Flow<DgfrCallState<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "site/context.jsonld"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getSiteDataPortal(format: String): Flow<DgfrCallState<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "site/data.$format"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getHomeDatasets(): Flow<DgfrCallState<List<Dataset>>> = loadingFlow {
        client.get(
            path = "site/home/datasets/"
        )
    }

    override fun putSetHomeDatasets(datasetIds: List<String>): Flow<DgfrCallState<List<Dataset>>> =
        loadingFlow {
            client.put(
                path = "site/home/datasets/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = datasetIds
            }
        }

    override fun getHomeReuses(): Flow<DgfrCallState<List<Reuse>>> = loadingFlow {
        client.get(
            path = "site/home/reuses/"
        )
    }

    override fun putSetHomeReuses(reuseIds: List<String>): Flow<DgfrCallState<List<Reuse>>> =
        loadingFlow {
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
        size: Int?
    ): Flow<DgfrCallState<List<Territory>>> = loadingFlow {
        client.get(
            path = "territory/suggest/"
        ) {
            parameter("q", q)
            parameter("size", size)
        }
    }
}
