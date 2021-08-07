package com.baptistecarlier.kotlin.datagouvfr.client

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.logger.DgfrHttpLogger
import com.baptistecarlier.kotlin.datagouvfr.client.models.DatasetPage
import com.baptistecarlier.kotlin.datagouvfr.client.models.User
import com.baptistecarlier.kotlin.datagouvfr.util.appendIfNotNull
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.*

class DgfrService(private val apiKey: String? = null, logging: Boolean = false) :
    DgfrServiceContract {

    private val tag = "DgfrService"
    private val timeOut: Long = 60_000
    private val host = "www.data.gouv.fr"
    private val basePath = "/api/1/"
    private val engineFactory = CIO

    private val client: HttpClient = HttpClient(engineFactory) {
        this.defaultRequest {
            url.host = this@DgfrService.host
            url.protocol = URLProtocol.HTTPS
            url.encodedPath = basePath + url.encodedPath
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                requestTimeout = timeOut
            }
        }

        if (logging) {
            install(Logging) {
                logger = DgfrHttpLogger()
                level = LogLevel.ALL
            }
        }
    }

    //@Headers(
    //    "X-Operation-ID: list_datasets",
    //    "Content-Type: application/json"
    //)
    override suspend fun listDatasets(
        q: String?,
        facets: List<String>?,
        tag: String?,
        badge: String?,
        organization: String?,
        owner: String?,
        license: String?,
        geozone: String?,
        granularity: String?,
        format: String?,
        schema: String?,
        schemaVersion: String?,
        resourceType: String?,
        reuses: String?,
        temporalCoverage: String?,
        featured: Boolean?,
        sort: String?,
        page: Int?,
        pageSize: Int?,
        xFields: String?
    ): Flow<DatasetPage?> = flow {
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("facets", facets)
            builder.appendIfNotNull("tag", tag)
            builder.appendIfNotNull("badge", badge)
            builder.appendIfNotNull("organization", organization)
            builder.appendIfNotNull("owner", owner)
            builder.appendIfNotNull("license", license)
            builder.appendIfNotNull("geozone", geozone)
            builder.appendIfNotNull("granularity", granularity)
            builder.appendIfNotNull("format", format)
            builder.appendIfNotNull("schema", schema)
            builder.appendIfNotNull("schema_version", schemaVersion)
            builder.appendIfNotNull("resource_type", resourceType)
            builder.appendIfNotNull("reuses", reuses)
            builder.appendIfNotNull("temporal_coverage", temporalCoverage)
            builder.appendIfNotNull("featured", featured)
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            //* @param xFields An optional fields mask (optional)
            //@retrofit2.http.Header("X-Fields") xFields: String?
            //builder.append("X=$elds&")

            Log.d(tag, "listDatasets / begin")
            val response = client.get<DatasetPage>(
                path = "datasets/?$builder"
            )
            Log.d(tag, "listDatasets / response = $response")

            response
        } catch (e: Exception) {
            Log.d(tag, "listDatasets / Crash = $e")
            e.printStackTrace()
            null
        }
        emit(value)
    }

    override suspend fun me(): Flow<User?> = flow {
        val value = try {
            Log.d(tag, "me / begin")
            val response = client.get<User>(
                path = "me/"
            ) {
                addApiKey()
            }
            Log.d(tag, "me / response = $response")

            response
        } catch (e: Exception) {
            Log.d(tag, "me / Crash = $e")
            e.printStackTrace()
            null
        }
        emit(value)
    }

    private fun HttpRequestBuilder.addApiKey() {
        header("X-API-KEY", apiKey)
    }

}

