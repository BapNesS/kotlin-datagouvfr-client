package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.Job
import com.baptistecarlier.kotlin.datagouvfr.client.model.Task
import kotlinx.coroutines.flow.Flow

/**
 * Asynchronous workers related operations
 */
internal interface WorkersApi: WithApiKey {

    /**
     * List all scheduled jobs
     */
    @OptIn(MissingFieldMapping::class)
    fun getListJobs(): Flow<DgfrCallState<List<Job>>>

    /**
     * Create a new scheduled job
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postJobsApi(payload: Job): Flow<DgfrCallState<Job>>

    /**
     * List all schedulable jobs
     */
    fun getJobsReferenceApi(): Flow<DgfrCallState<List<String>>>

    /**
     * Delete a single scheduled job
     * @param id A job ID (required)
     */
    fun deleteJobApi(id: String): Flow<DgfrCallState<Boolean>>

    /**
     * Fetch a single scheduled job
     * @param id A job ID (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getJobApi(id: String): Flow<DgfrCallState<Job>>

    /**
     * Update a single scheduled job
     * @param id A job ID (required)
     */
    @kotlin.OptIn(com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping::class)
    fun putJobApi(id: String): Flow<DgfrCallState<Job>>

    /**
     * Get a tasks status given its ID
     * @param id (required)
     */
    fun getTaskApi(id: String): Flow<DgfrCallState<Task>>
}
