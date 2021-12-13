package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Site global namespace
 */
interface SiteApi : WithApiKey {

    /**
     * Fetch site activity, optionally filtered by user of org
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param user Filter activities for that particular user (optional)
     * @param organization Filter activities for that particular organization (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun getActivity(
        page: Int? = null,
        pageSize: Int? = null,
        user: String? = null,
        organization: String? = null
    ): Flow<DgfrCallState<List<ActivityPage>>>

    /**
     * An OEmbed compliant API endpoint
     * See: http://oembed.com/  Support datasets and reuses URLs
     * @param url The URL to retrieve embedding information for (required)
     * @param maxwidth The maximum width of the embedded resource (optional)
     * @param maxheight The maximum height of the embedded resource (optional)
     * @param format The required response format. (optional, default to json)
     */
    fun getOembed(
        url: String,
        maxWidth: String? = null,
        maxHeight: String? = null,
        format: String? = null
    ): Flow<DgfrCallState<Oembed>>

    /**
     * The returned payload is a list of OEmbed formatted responses
     * See: http://oembed.com/
     * @param references are composed by a keyword (`kind`) followed by the `id` each of those
     * separated by commas.
     * E.g: dataset-5369992aa3a729239d205183, territory-fr:departement:33@1860-07-01:emploi_dep
     * Only datasets and territories are supported for now.
     */
    fun getOembeds(references: String): Flow<DgfrCallState<List<Oembed>>>

    /**
     * Site-wide variables
     */
    fun getSite(): Flow<DgfrCallState<Site>>

    /**
     * Root RDF endpoint with content negociation handling
     * @return Content as [String]
     */
    fun getSiteRdfCatalog(): Flow<DgfrCallState<String>>

    /**
     * Root RDF endpoint with content negociation handling in specified [format]
     * @param format (required)
     * @return Content as [String]
     */
    fun getSiteRdfCatalogFormat(format: String): Flow<DgfrCallState<String>>

    /**
     * @return Content as [String]
     */
    fun getSiteJsonLdContext(): Flow<DgfrCallState<String>>

    /**
     * Root RDF endpoint with content negociation handling
     * @param format (required)
     */
    fun getSiteDataPortal(format: String): Flow<DgfrCallState<String>>

    /**
     * List homepage datasets
     */
    fun getHomeDatasets(): Flow<DgfrCallState<List<Dataset>>>

    /**
     * Set the homepage datasets editorial selection
     * @param datasetIds Dataset IDs to put in homepage
     */
    fun putSetHomeDatasets(datasetIds: List<String>): Flow<DgfrCallState<List<Dataset>>>

    /**
     * List homepage featured reuses
     */
    fun getHomeReuses(): Flow<DgfrCallState<List<Reuse>>>

    /**
     * Set the homepage reuses editorial selection
     * @param reuseIds Reuse IDs to put in homepage (required)
     */
    fun putSetHomeReuses(reuseIds: List<String>): Flow<DgfrCallState<List<Reuse>>>

    /**
     * @param q The string to autocomplete/suggest (required)
     * @param size The maximum result size (optional)
     */
    fun getSuggestTerritory(
        q: String,
        size: Int? = null
    ): Flow<DgfrCallState<List<Territory>>>
}
