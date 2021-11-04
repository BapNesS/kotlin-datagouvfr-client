package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Harvest related operations
 */
internal interface HarvestApi : WithApiKey {

    /**
     * List all available harvest backends
     */
    fun getHarvestBackends(): Flow<DgfrResource<HarvestBackend>>

    /**
     * List all jobs for a given source
     * @param ident (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getHarvestJob(ident: String, page: Int?, pageSize: Int?): Flow<DgfrResource<HarvestJobPage>>

    /**
     * List all available harvesters
     */
    fun getListHarvesterApi(): Flow<DgfrResource<List<String>>>

    /**
     * Preview an harvesting from a source created with the given payload
     * @param payload (required)
     */
    fun postPreviewHarvestSourceConfig(payload: HarvestSource): Flow<DgfrResource<HarvestJobPreview>>

    /**
     * @param ident A source ID or slug (required)
     */
    fun deleteHarvestSource(ident: String): Flow<DgfrResource<HarvestSource>>

    /**
     * Get a single source given an ID or a slug
     * @param ident A source ID or slug (required)
     */
    fun getHarvestSource(ident: String): Flow<DgfrResource<HarvestSource>>

    /**
     * Update a harvest source
     * @param ident A source ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateHarvestSource(ident: String, payload: HarvestSource): Flow<DgfrResource<HarvestSource>>

    /**
     * List all jobs for a given source
     * @param ident (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    fun getListHarvestJobs(ident: String, page: Int?, pageSize: Int?): Flow<DgfrResource<HarvestJob>>

    /**
     * Preview a single harvest source given an ID or a slug
     * @param ident A source ID or slug (required)
     */
    fun getPreviewHarvestSource(ident: String): Flow<DgfrResource<HarvestJobPreview>>

    /**
     * Unschedule an harvest source
     * @param ident A source ID or slug (required)
     */
    fun deleteUnscheduleHarvestSource(ident: String): Flow<DgfrResource<HarvestSource>>

    /**
     * Schedule an harvest source
     * @param ident A source ID or slug (required)
     * @param payload A cron expression (required)
     */
    fun postScheduleHarvestSource(ident: String, payload: String): Flow<DgfrResource<HarvestSource>>

    /**
     * Validate or reject an harvest source
     * @param ident A source ID or slug (required)
     * @param payload (required)
     */
    fun postValidateHarvestSource(ident: String, payload: HarvestSourceValidation): Flow<DgfrResource<HarvestSource>>

    /**
     * List all harvest sources
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param owner The organization or user ID to filter on (optional)
     * @param deleted Include sources flaggued as deleted (optional, default to false)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListHarvestSources(page: Int?, pageSize: Int?, owner: String?, deleted: Boolean?): Flow<DgfrResource<List<HarvestSourcePage>>>

    /**
     * Create a new harvest source
     * @param payload (required)
     */
    fun postCreateHarvestSource(payload: HarvestSource): Flow<DgfrResource<HarvestSource>>
}
