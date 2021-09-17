package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoGranularity
import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoJSONFeatureCollection
import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoLevel
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.Draft
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
                path = "spatial/coverage/{$level}/"
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

    @Draft
    override suspend fun getSpatialZone(id: String): Flow<Any?> {
        TODO("Not yet implemented")
    }


    @Draft
    override suspend fun getSpatialZoneChildren(id: String): Flow<Any?> {
        TODO("Not yet implemented")
    }


    @Draft
    override suspend fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean?,
        size: Int?
    ): Flow<Any?> {
        TODO("Not yet implemented")
    }


    @Draft
    override suspend fun getSuggestZones(q: String, size: Int?): Flow<Any?> {
        TODO("Not yet implemented")
    }


    @Draft
    override suspend fun getSpatialZones(ids: List<String>): Flow<Any?> {
        TODO("Not yet implemented")
    }

}


