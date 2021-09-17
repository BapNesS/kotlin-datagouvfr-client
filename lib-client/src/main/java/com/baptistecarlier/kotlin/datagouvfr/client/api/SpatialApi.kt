package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoGranularity
import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoJSONFeatureCollection
import com.baptistecarlier.kotlin.datagouvfr.client.models.GeoLevel
import io.ktor.client.*
import kotlinx.coroutines.flow.Flow

/**
 * Spatial references
 */
interface SpatialApi {

    /**
     * List each zone for a given level with their datasets count
     * @param level
     */
    suspend fun getSpatialCoverage(level: String) : Flow<List<GeoJSONFeatureCollection>?>

    /**
     * List all known spatial granularities
     */
    suspend fun getSpatialGranularities() : Flow<List<GeoGranularity>?>

    /**
     * List all known levels
     */
    suspend fun getSpatialLevels() : Flow<List<GeoLevel>?>

    /**
     * Fetch a zone
     * @param zone A zone identifier
     */
    suspend fun getSpatialZone(id: String) : Flow<Any?>

    /**
     * Fetch children of a zone
     * @param zone A zone identifier
     */
    suspend fun getSpatialZoneChildren(id: String) : Flow<Any?>

    /**
     * Fetch datasets for a given zone
     * @param zone A zone identifier
     * @param dynamic Append dynamic datasets
     * @param size The amount of datasets to fetch (Default value: 25)
     */
    suspend fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean? = null,
        size: Int? = null
    ) : Flow<Any?>

    /**
     * Suggest geospatial zones
     * @param q The string to autocomplete/suggest
     * @param size The amount of suggestion to fetch (Default value: 10)
     */
    suspend fun getSuggestZones(
        q: String,
        size: Int? = null
    ) : Flow<Any?>

    /**
     * Fetch a zone list as GeoJSON
     * @param ids A zone identifiers list
     */
    suspend fun getSpatialZones(
        ids: List<String>
    ) : Flow<Any?>

}
