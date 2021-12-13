package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Harvest related operations
 */
interface HarvestApi : WithApiKey {

    /**
     * List all available harvest backends
     */
    fun getHarvestBackends(): Flow<DgfrCallState<HarvestBackend>>

    /**
     * List all jobs for a given source
     * @param ident (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getHarvestJob(ident: String, page: Int?, pageSize: Int?): Flow<DgfrCallState<HarvestJobPage>>

    /**
     * List all available harvesters
     */
    fun getListHarvesterApi(): Flow<DgfrCallState<List<String>>>

    /**
     * Preview an harvesting from a source created with the given payload
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postPreviewHarvestSourceConfig(payload: HarvestSource): Flow<DgfrCallState<HarvestJobPreview>>

    /**
     * @param ident A source ID or slug (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun deleteHarvestSource(ident: String): Flow<DgfrCallState<HarvestSource>>

    /**
     * Get a single source given an ID or a slug
     * @param ident A source ID or slug (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getHarvestSource(ident: String): Flow<DgfrCallState<HarvestSource>>

    /**
     * Update a harvest source
     * @param ident A source ID or slug (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun putUpdateHarvestSource(ident: String, payload: HarvestSource): Flow<DgfrCallState<HarvestSource>>

    /**
     * List all jobs for a given source
     * @param ident (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    fun getListHarvestJobs(ident: String, page: Int?, pageSize: Int?): Flow<DgfrCallState<HarvestJob>>

    /**
     * Preview a single harvest source given an ID or a slug
     * @param ident A source ID or slug (required)
     */
    fun getPreviewHarvestSource(ident: String): Flow<DgfrCallState<HarvestJobPreview>>

    /**
     * Unschedule an harvest source
     * @param ident A source ID or slug (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun deleteUnscheduleHarvestSource(ident: String): Flow<DgfrCallState<HarvestSource>>

    /**
     * Schedule an harvest source
     * @param ident A source ID or slug (required)
     * @param payload A cron expression (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postScheduleHarvestSource(ident: String, payload: String): Flow<DgfrCallState<HarvestSource>>

    /**
     * Validate or reject an harvest source
     * @param ident A source ID or slug (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postValidateHarvestSource(ident: String, payload: HarvestSourceValidation): Flow<DgfrCallState<HarvestSource>>

    /**
     * List all harvest sources
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param owner The organization or user ID to filter on (optional)
     * @param deleted Include sources flaggued as deleted (optional, default to false)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListHarvestSources(page: Int?, pageSize: Int?, owner: String?, deleted: Boolean?): Flow<DgfrCallState<List<HarvestSourcePage>>>

    /**
     * Create a new harvest source
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateHarvestSource(payload: HarvestSource): Flow<DgfrCallState<HarvestSource>>
}
