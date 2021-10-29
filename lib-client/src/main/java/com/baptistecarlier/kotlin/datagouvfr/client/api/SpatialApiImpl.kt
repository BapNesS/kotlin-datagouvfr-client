package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

internal class SpatialApiImpl(private val client: HttpClient): SpatialApi {

    override fun getSpatialCoverage(level: String): Flow<DgfrResource<List<GeoJSONFeatureCollection>>> = loadingFlow {
        client.get(
            path = "spatial/coverage/$level/"
        )
    }

    override fun getSpatialGranularities(): Flow<DgfrResource<List<GeoGranularity>>> = loadingFlow {
        client.get(
            path = "spatial/granularities/"
        )
    }

    override fun getSpatialLevels(): Flow<DgfrResource<List<GeoLevel>>> = loadingFlow {
        client.get(
            path = "spatial/levels/"
        )
    }

    override fun getSpatialZone(id: String): Flow<DgfrResource<GeoJSONFeature>> = loadingFlow {
        client.get(
            path = "spatial/zone/$id/"
        )
    }

    override fun getSpatialZoneChildren(id: String): Flow<DgfrResource<List<GeoJSONFeatureCollection>>> = loadingFlow {
        client.get(
            path = "spatial/zone/$id/children/"
        )
    }

    override fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean?,
        size: Int?
    ) : Flow<DgfrResource<List<DatasetReference>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("id", id)
        builder.appendIfNotNull("dynamic", dynamic)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "spatial/zone/{id}/datasets/?${builder.urlEncore()}"
        )
    }

    override fun getSuggestZones(
        q: String,
        size: Int?
    ): Flow<DgfrResource<List<TerritorySuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "spatial/zones/suggest/?${builder.urlEncore()}"
        )
    }

    override fun getSpatialZones(ids: List<String>): Flow<DgfrResource<GeoJSONFeatureCollection>> = loadingFlow {
        client.get(
            path = "spatial/zones/?${ids}/"
        )
    }

}


