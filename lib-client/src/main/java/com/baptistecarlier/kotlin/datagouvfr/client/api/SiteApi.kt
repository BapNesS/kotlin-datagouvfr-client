package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Site global namespace
 */
interface SiteApi: WithApiKey {

    /**
     * Fetch site activity, optionally filtered by user of org
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param user Filter activities for that particular user (optional)
     * @param organization Filter activities for that particular organization (optional)
     */
    suspend fun getActivity(
        page: Int? = null,
        pageSize: Int? = null,
        user: String? = null,
        organization: String? = null
    ): Flow<DgfrResource<List<ActivityPage>>>

    /**
     * An OEmbed compliant API endpoint
     * See: http://oembed.com/  Support datasets and reuses URLs
     * @param url The URL to retrieve embedding information for (required)
     * @param maxwidth The maximum width of the embedded resource (optional)
     * @param maxheight The maximum height of the embedded resource (optional)
     * @param format The required response format. (optional, default to json)
     */
    suspend fun getOembed(
        url: String,
        maxWidth: String? = null,
        maxHeight: String? = null,
        format: String? = null): Flow<DgfrResource<Oembed>>

    /**
     * The returned payload is a list of OEmbed formatted responses
     * See: http://oembed.com/
     * @param references are composed by a keyword (`kind`) followed by the `id` each of those
     * separated by commas.
     * E.g: dataset-5369992aa3a729239d205183, territory-fr:departement:33@1860-07-01:emploi_dep
     * Only datasets and territories are supported for now.
     */
    suspend fun getOembeds(references: String): Flow<DgfrResource<List<Oembed>>>

    /**
     * Site-wide variables
     */
    suspend fun getSite(): Flow<DgfrResource<Site>>

    /**
     * Root RDF endpoint with content negociation handling
     * @return Content as [String]
     */
    suspend fun getSiteRdfCatalog(): Flow<DgfrResource<String>>

    /**
     * Root RDF endpoint with content negociation handling in specified [format]
     * @param format (required)
     * @return Content as [String]
     */
    suspend fun getSiteRdfCatalogFormat(format: String): Flow<DgfrResource<String>>

    /**
     * @return Content as [String]
     */
    suspend fun getSiteJsonLdContext(): Flow<DgfrResource<String>>

    /**
     * Root RDF endpoint with content negociation handling
     * @param format (required)
     */
    suspend fun getSiteDataPortal(format: String): Flow<DgfrResource<String>>

    /**
     * List homepage datasets
     */
    suspend fun getHomeDatasets(): Flow<DgfrResource<List<Dataset>>>

    /**
     * Set the homepage datasets editorial selection
     * @param datasetIds Dataset IDs to put in homepage
     */
    suspend fun putSetHomeDatasets(datasetIds: List<String>): Flow<DgfrResource<List<Dataset>>>

    /**
     * List homepage featured reuses
     */
    suspend fun getHomeReuses(): Flow<DgfrResource<List<Reuse>>>

    /**
     * Set the homepage reuses editorial selection
     * @param reuseIds Reuse IDs to put in homepage (required)
     */
    suspend fun putSetHomeReuses(reuseIds: List<String>): Flow<DgfrResource<List<Reuse>>>

    /**
     * @param q The string to autocomplete/suggest (required)
     * @param size The maximum result size (optional)
     */
    suspend fun getSuggestTerritory(
        q: String,
        size: Int? = null
    ): Flow<DgfrResource<List<Territory>>>

}