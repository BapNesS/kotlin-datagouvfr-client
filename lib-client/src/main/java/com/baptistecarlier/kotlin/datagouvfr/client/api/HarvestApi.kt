package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Harvest related operations
 */
interface HarvestApi: WithApiKey {

    /**
     * List all available harvest backends
     */
    suspend fun getHarvestBackends(): Flow<HarvestBackend?>

    /**
     * List all jobs for a given source
     * @param ident (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getHarvestJob(ident: String, page: Int?, pageSize: Int?): Flow<HarvestJobPage?>

    /**
     * List all available harvesters
     */
    suspend fun getListHarvesterApi(): Flow<List<String>?>

    /**
     * Preview an harvesting from a source created with the given payload
     * @param payload (required)
     */
    suspend fun postPreviewHarvestSourceConfig(payload: HarvestSource): Flow<HarvestJobPreview?>

    /**
     * @param ident A source ID or slug (required)
     */
    suspend fun deleteHarvestSource(ident: String): Flow<HarvestSource?>

    /**
     * Get a single source given an ID or a slug
     * @param ident A source ID or slug (required)
     */
    suspend fun getHarvestSource(ident: String): Flow<HarvestSource?>

    /**
     * Update a harvest source
     * @param ident A source ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateHarvestSource(ident: String, payload: HarvestSource): Flow<HarvestSource?>

    /**
     * List all jobs for a given source
     * @param ident (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getListHarvestJobs(ident: String, page: Int?, pageSize: Int?): Flow<HarvestJob?>

    /**
     * Preview a single harvest source given an ID or a slug
     * @param ident A source ID or slug (required)
     */
    suspend fun getPreviewHarvestSource(ident: String): Flow<HarvestJobPreview?>

    /**
     * Unschedule an harvest source
     * @param ident A source ID or slug (required)
     */
    suspend fun deleteUnscheduleHarvestSource(ident: String): Flow<HarvestSource?>

    /**
     * Schedule an harvest source
     * @param ident A source ID or slug (required)
     * @param payload A cron expression (required)
     */
    suspend fun postScheduleHarvestSource(ident: String, payload: String): Flow<HarvestSource?>

    /**
     * Validate or reject an harvest source
     * @param ident A source ID or slug (required)
     * @param payload (required)
     */
    suspend fun postValidateHarvestSource(ident: String, payload: HarvestSourceValidation): Flow<HarvestSource?>

    /**
     * List all harvest sources
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param owner The organization or user ID to filter on (optional)
     * @param deleted Include sources flaggued as deleted (optional, default to false)
     */
    suspend fun getListHarvestSources(page: Int?, pageSize: Int?, owner: String?, deleted: Boolean?): Flow<List<HarvestSourcePage>?>

    /**
     * Create a new harvest source
     * @param payload (required)
     */
    suspend fun postCreateHarvestSource(payload: HarvestSource): Flow<HarvestSource?>

}