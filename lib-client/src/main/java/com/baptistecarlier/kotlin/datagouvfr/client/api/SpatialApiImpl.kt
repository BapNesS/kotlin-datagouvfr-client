package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.Draft
import com.baptistecarlier.kotlin.datagouvfr.client.models.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpatialApiImpl(private val client: HttpClient) : SpatialApi {

    private val tag = "SpatialApiImpl"

    override suspend fun getSpatialCoverage(level: String): Flow<List<GeoJSONFeatureCollection>?> = flow {
        Log.d(tag, "getSpatialCoverage / begin")
        val value = try {
            val response = client.get<List<GeoJSONFeatureCollection>?>(
                path = "spatial/coverage/$level/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialCoverage / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSpatialGranularities(): Flow<List<GeoGranularity>?> = flow {
        Log.d(tag, "getSpatialGranularities / begin")
        val value = try {
            val response = client.get<List<GeoGranularity>>(
                path = "spatial/granularities/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialGranularities / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSpatialLevels(): Flow<List<GeoLevel>?> = flow {
        Log.d(tag, "getSpatialLevels / begin")
        val value = try {
            val response = client.get<List<GeoLevel>?>(
                path = "spatial/levels/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialLevels / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSpatialZone(id: String): Flow<GeoJSONFeature?> = flow {
        Log.d(tag, "getSpatialZone / begin")
        val value = try {
            val response = client.get<GeoJSONFeature?>(
                path = "spatial/zone/$id/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialZone / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSpatialZoneChildren(id: String): Flow<List<GeoJSONFeatureCollection>?> = flow {
        Log.d(tag, "getSpatialZoneChildren / begin")
        val value = try {
            val response = client.get<List<GeoJSONFeatureCollection>?>(
                path = "spatial/zone/$id/children/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialZoneChildren / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean?,
        size: Int?
    ) : Flow<List<DatasetReference>?> = flow {
        Log.d(tag, "getSpatialZoneDatasets / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("id", id)
            builder.appendIfNotNull("dynamic", dynamic)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<DatasetReference>?>(
                path = "spatial/zone/{id}/datasets/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialZoneDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestZones(
        q: String,
        size: Int?
    ): Flow<List<TerritorySuggestion>?> = flow {
        Log.d(tag, "getSuggestZones / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<TerritorySuggestion>?>(
                path = "spatial/zones/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestZones / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSpatialZones(ids: List<String>): Flow<GeoJSONFeatureCollection?> = flow {
        Log.d(tag, "getSpatialZones / begin")
        val value = try {
            val response = client.get<GeoJSONFeatureCollection?>(
                path = "spatial/zones/?${ids}/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSpatialZones / Exception =  $e")
            null
        }
        emit(value)
    }

}


