package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoGranularity
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class SpatialApiImpl(private val client: HttpClient) : SpatialApi {

    private val tag = "SpatialApiImpl"

    override suspend fun getSpatialCoverage(level: String): Flow<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpatialGranularities(): Flow<List<GeoGranularity>?> = flow {
        val value = try {
            val response = client.get<List<GeoGranularity>>()
            response
        } catch (e: Exception) {
            null
        }
        emit(value)
    }

    override suspend fun getSpatialLevels(): Flow<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpatialZone(id: String): Flow<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpatialZoneChildren(id: String): Flow<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean?,
        size: Int?
    ): Flow<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSuggestZones(q: String, size: Int?): Flow<Any?> {
        TODO("Not yet implemented")
    }

    override suspend fun getSpatialZones(ids: List<String>): Flow<Any?> {
        TODO("Not yet implemented")
    }

}


