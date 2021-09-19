package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
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
import kotlinx.coroutines.flow.flow

class SiteApiImpl(private val client: HttpClient): SiteApi {

    private val tag = "SiteApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getActivity(
        page: Int?,
        pageSize: Int?,
        user: String?,
        organization: String?)
    : Flow<List<ActivityPage>?> = flow {
        Log.d(tag, "getActivity / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)
            builder.appendIfNotNull("user", user)
            builder.appendIfNotNull("organization", user)

            val response = client.get<List<ActivityPage>?>(
                path = "activity/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getActivity / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getOembed(
        url: String,
        maxWidth: String?,
        maxHeight: String?,
        format: String?)
    : Flow<Oembed?> = flow {
        Log.d(tag, "getOembed / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("url", url)
            builder.appendIfNotNull("maxwidth", maxWidth)
            builder.appendIfNotNull("maxheight", maxHeight)
            builder.appendIfNotNull("format", format)

            val response = client.get<Oembed?>(
                path = "oembed/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getOembed / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getOembeds(references: String): Flow<List<Oembed>?> = flow {
        Log.d(tag, "getOembeds / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("references", references)

            val response = client.get<List<Oembed>?>(
                path = "oembeds/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getOembeds / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSite(): Flow<Site?> = flow {
        Log.d(tag, "getSite / begin")
        val value = try {
            val response = client.get<Site?>(
                path = "site/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSite / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSiteRdfCatalog(): Flow<String?> = flow {
        Log.d(tag, "getSiteRdfCatalog / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "site/catalog"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getSiteRdfCatalog / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSiteRdfCatalogFormat(format: String): Flow<String?> = flow {
        Log.d(tag, "getSiteRdfCatalogFormat / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "site/catalog.$format"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getSiteRdfCatalogFormat / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSiteJsonLdContext(): Flow<String?> = flow {
        Log.d(tag, "getSiteJsonLdContext / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "site/context.jsonld"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getSiteJsonLdContext / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSiteDataPortal(format: String): Flow<String?> = flow {
        Log.d(tag, "getSiteRdfCatalogFormat / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "site/data.$format"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getSiteRdfCatalogFormat / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getHomeDatasets(): Flow<List<Dataset>?> = flow {
        Log.d(tag, "getHomeDatasets / begin")
        val value = try {
            val response = client.get<List<Dataset>?>(
                path = "site/home/datasets/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getHomeDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putSetHomeDatasets(datasetIds: List<String>): Flow<List<Dataset>?> = flow {
        Log.d(tag, "putSetHomeDatasets / begin")
        val value = try {
            val response = client.put<List<Dataset>?>(
                path = "site/home/datasets/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = datasetIds
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putSetHomeDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getHomeReuses(): Flow<List<Reuse>?> = flow {
        Log.d(tag, "getHomeReuses / begin")
        val value = try {
            val response = client.get<List<Reuse>?>(
                path = "site/home/reuses/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getHomeReuses / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putSetHomeReuses(reuseIds: List<String>): Flow<List<Reuse>?> = flow {
        Log.d(tag, "putSetHomeReuses / begin")
        val value = try {
            val response = client.put<List<Reuse>?>(
                path = "site/home/reuses/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = reuseIds
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putSetHomeReuses / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestTerritory(
        q: String,
        size: Int?)
    : Flow<List<Territory>?> = flow {
        Log.d(tag, "getSuggestTerritory / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<Territory>?>(
                path = "territory/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestTerritory / Exception =  $e")
            null
        }
        emit(value)
    }

}