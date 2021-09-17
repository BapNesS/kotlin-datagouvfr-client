package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.client.models.DatasetPage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DatasetsApiImpl(private val client: HttpClient) : DatasetsApi {

    private val tag = "DatasetApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
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
        Log.d(tag, "listDatasets / begin")
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

            val response = client.get<DatasetPage>(
                path = "datasets/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "listDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getDataset(id: String): Flow<Dataset?> = flow {
        Log.d(tag, "getDataset / begin")
        val value = try {
            val response = client.get<Dataset>(
                path = "datasets/$id/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getDataset / Exception =  $e")
            null
        }
        emit(value)
    }
}