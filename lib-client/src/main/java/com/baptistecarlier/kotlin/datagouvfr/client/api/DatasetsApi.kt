package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.models.Dataset
import com.baptistecarlier.kotlin.datagouvfr.client.models.DatasetPage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

interface DatasetsApi: WithApiKey {

    /**
     * List or search all datasets
     * The endpoint is owned by kotlincoroutines service owner
     * @param q The search query (optional)
     * @param facets Selected facets to fetch (optional)
     * @param tag (optional)
     * @param badge (optional)
     * @param organization (optional)
     * @param owner (optional)
     * @param license (optional)
     * @param geozone (optional)
     * @param granularity (optional)
     * @param format (optional)
     * @param schema (optional)
     * @param schemaVersion (optional)
     * @param resourceType (optional)
     * @param reuses (optional)
     * @param temporalCoverage Une couverture temporelle est exprimée par début-fin où les deux dates sont au format ISO (c&#39;est-à-dire YYYY-MM-DD-YYYY-MM-DD) (optional)
     * @param featured (optional)
     * @param sort The field (and direction) on which sorting apply (optional)
     * @param page The page to display (optional, default to 0)
     * @param pageSize The page size (optional, default to 20)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun listDatasets(
        q: String? = null,
        facets: List<String>? = null,
        tag: String? = null,
        badge: String? = null,
        organization: String? = null,
        owner: String? = null,
        license: String? = null,
        geozone: String? = null,
        granularity: String? = null,
        format: String? = null,
        schema: String? = null,
        schemaVersion: String? = null,
        resourceType: String? = null,
        reuses: String? = null,
        temporalCoverage: String? = null,
        featured: Boolean? = null,
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null,
        xFields: String? = null
    ): Flow<DatasetPage?>

    suspend fun getDataset(id: String): Flow<Dataset?>
}