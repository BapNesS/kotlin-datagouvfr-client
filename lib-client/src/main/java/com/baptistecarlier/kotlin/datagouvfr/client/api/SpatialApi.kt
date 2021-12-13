package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Spatial references
 */
interface SpatialApi {

    /**
     * List each zone for a given level with their datasets count
     * @param level
     */
    fun getSpatialCoverage(level: String): Flow<DgfrResource<List<GeoJSONFeatureCollection>>>

    /**
     * List all known spatial granularities
     */
    fun getSpatialGranularities(): Flow<DgfrResource<List<GeoGranularity>>>

    /**
     * List all known levels
     */
    fun getSpatialLevels(): Flow<DgfrResource<List<GeoLevel>>>

    /**
     * Fetch a zone
     * @param id A zone identifier
     */
    fun getSpatialZone(id: String): Flow<DgfrResource<GeoJSONFeature>>

    /**
     * Fetch children of a zone
     * @param id A zone identifier
     */
    fun getSpatialZoneChildren(id: String): Flow<DgfrResource<List<GeoJSONFeatureCollection>>>

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
    ): Flow<DgfrResource<List<DatasetReference>>>

    /**
     * Suggest geospatial zones
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestZones(
        q: String,
        size: Int? = null
    ): Flow<DgfrResource<List<TerritorySuggestion>>>

    /**
     * Fetch a zone list as GeoJSON
     * @param ids A zone identifiers list (comma separated) (required)
     */
    fun getSpatialZones(
        ids: List<String>
    ): Flow<DgfrResource<GeoJSONFeatureCollection>>
}
