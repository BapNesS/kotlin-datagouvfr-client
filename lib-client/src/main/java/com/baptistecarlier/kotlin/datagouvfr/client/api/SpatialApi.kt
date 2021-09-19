package com.baptistecarlier.kotlin.datagouvfr.client.api

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
     * @param id A zone identifier
     */
    suspend fun getSpatialZone(id: String) : Flow<GeoJSONFeature?>

    /**
     * Fetch children of a zone
     * @param id A zone identifier
     */
    suspend fun getSpatialZoneChildren(id: String) : Flow<List<GeoJSONFeatureCollection>?>

    /**
     * Fetch datasets for a given zone
     * @param id A zone identifier (required)
     * @param dynamic Append dynamic datasets (optional)
     * @param size The amount of datasets to fetch (optional, default to 25)
     */
    suspend fun getSpatialZoneDatasets(
        id: String,
        dynamic: Boolean? = null,
        size: Int? = null
    ) : Flow<List<DatasetReference>?>

    /**
     * Suggest geospatial zones
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    suspend fun getSuggestZones(
        q: String,
        size: Int? = null
    ) : Flow<List<TerritorySuggestion>?>

    /**
     * Fetch a zone list as GeoJSON
     * @param ids A zone identifiers list (comma separated) (required)
     */
    suspend fun getSpatialZones(
        ids: List<String>
    ) : Flow<GeoJSONFeatureCollection?>

}
