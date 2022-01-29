package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Spatial references
 */
internal interface SpatialApi {

    /**
     * List each zone for a given level with their datasets count
     * @param level
     */
    fun getSpatialCoverage(level: String): Flow<DgfrCallState<List<GeoJSONFeatureCollection>>>

    /**
     * List all known spatial granularities
     */
    fun getSpatialGranularities(): Flow<DgfrCallState<List<GeoGranularity>>>

    /**
     * List all known levels
     */
    fun getSpatialLevels(): Flow<DgfrCallState<List<GeoLevel>>>

    /**
     * Fetch a zone
     * @param id A zone identifier
     */
    @OptIn(MissingFieldMapping::class)
    fun getSpatialZone(id: String): Flow<DgfrCallState<GeoJSONFeature>>

    /**
     * Fetch children of a zone
     * @param id A zone identifier
     */
    fun getSpatialZoneChildren(id: String): Flow<DgfrCallState<List<GeoJSONFeatureCollection>>>

    /**
     * Fetch datasets for a given zone
     * @param id A zone identifier (required)
     * @param dynamic Append dynamic datasets (optional)
     * @param size The amount of datasets to fetch (optional, default to 25)
     */
    fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean? = null,
        size: Int? = null
    ): Flow<DgfrCallState<List<DatasetReference>>>

    /**
     * Suggest geospatial zones
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestZones(
        q: String,
        size: Int? = null
    ): Flow<DgfrCallState<List<TerritorySuggestion>>>

    /**
     * Fetch a zone list as GeoJSON
     * @param ids A zone identifiers list (comma separated) (required)
     */
    fun getSpatialZones(
        ids: List<String>
    ): Flow<DgfrCallState<GeoJSONFeatureCollection>>
}
