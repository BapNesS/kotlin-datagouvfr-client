package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Connected user related operations
 */
interface MeApi: WithApiKey {

    /**
     * Delete my profile
     */
    suspend fun deleteMe(): Flow<Boolean?>

    /**
     * Fetch the current user (me) identity
     */
    suspend fun getMe(): Flow<Me?>

    /**
     * Update my profile
     * @param payload (required)
     */
    suspend fun putUpdateMe(payload: Me): Flow<Me?>

    /**
     * Clear/destroy an apikey
     */
    suspend fun deleteClearApikey(): Flow<Boolean?>

    /**
     * (Re)Generate my API Key
     */
    suspend fun postGenerateApikey(): Flow<ApiKey?>
    /**
     * Upload a new avatar
     * @param file (optional)
     * @param bbox (optional)
     */

    /*suspend fun postMyAvatar(bbox: String?): Flow<UploadedImage?>*/

    /**
     * List all my datasets (including private ones)
     */
    suspend fun getMyDatasets(): Flow<List<Dataset>?>

    /**
     * Fetch the current user (me) metrics
     */
    suspend fun getMyMetrics(): Flow<List<MyMetrics>?>

    /**
     * List all community resources related to me and my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgCommunityResources(q: String?): Flow<List<CommunityResource>?>

    /**
     * List all datasets related to me and my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgDatasets(q: String?): Flow<List<Dataset>?>

    /**
     * List all discussions related to my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgDiscussions(q: String?): Flow<List<Discussion>?>

    /**
     * List all issues related to my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgIssues(q: String?): Flow<List<Issue>?>

    /**
     * List all reuses related to me and my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgReuses(q: String?): Flow<List<Reuse>?>

    /**
     * List all my reuses (including private ones)
     */
    suspend fun getMyReuses(): Flow<List<Reuse>?>

}

