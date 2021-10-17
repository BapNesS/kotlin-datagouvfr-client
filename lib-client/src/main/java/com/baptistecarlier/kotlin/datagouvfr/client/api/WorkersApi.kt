package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Job
import com.baptistecarlier.kotlin.datagouvfr.client.model.Task
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Asynchronous workers related operations
 */
interface WorkersApi : WithApiKey {

    /**
     * List all scheduled jobs
     */
    fun getListJobs(): Flow<DgfrResource<List<Job>>>

    /**
     * Create a new scheduled job
     * @param payload (required)
     */
    fun postJobsApi(payload: Job): Flow<DgfrResource<Job>>

    /**
     * List all schedulable jobs
     */
    fun getJobsReferenceApi(): Flow<DgfrResource<List<String>>>

    /**
     * Delete a single scheduled job
     * @param id A job ID (required)
     */
    fun deleteJobApi(id: String): Flow<DgfrResource<Boolean>>

    /**
     * Fetch a single scheduled job
     * @param id A job ID (required)
     */
    fun getJobApi(id: String): Flow<DgfrResource<Job>>

    /**
     * Update a single scheduled job
     * @param id A job ID (required)
     */
    fun putJobApi(id: String): Flow<DgfrResource<Job>>

    /**
     * Get a tasks status given its ID
     * @param id (required)
     */
    fun getTaskApi(id: String): Flow<DgfrResource<Task>>

}