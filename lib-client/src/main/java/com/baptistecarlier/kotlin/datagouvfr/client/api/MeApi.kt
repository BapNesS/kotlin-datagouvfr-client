package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
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
    suspend fun deleteMe(): Flow<DgfrResource<Boolean>>

    /**
     * Fetch the current user (me) identity
     */
    suspend fun getMe(): Flow<DgfrResource<Me>>

    /**
     * Update my profile
     * @param payload (required)
     */
    suspend fun putUpdateMe(payload: Me): Flow<DgfrResource<Me>>

    /**
     * Clear/destroy an apikey
     */
    suspend fun deleteClearApikey(): Flow<DgfrResource<Boolean>>

    /**
     * (Re)Generate my API Key
     */
    suspend fun postGenerateApikey(): Flow<DgfrResource<ApiKey>>

    /**
     * Upload a new avatar
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    suspend fun postMyAvatar(
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>>

    /**
     * List all my datasets (including private ones)
     */
    suspend fun getMyDatasets(): Flow<DgfrResource<List<Dataset>>>

    /**
     * Fetch the current user (me) metrics
     */
    suspend fun getMyMetrics(): Flow<DgfrResource<List<MyMetrics>>>

    /**
     * List all community resources related to me and my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgCommunityResources(q: String? = null): Flow<DgfrResource<List<CommunityResource>>>

    /**
     * List all datasets related to me and my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgDatasets(q: String? = null): Flow<DgfrResource<List<Dataset>>>

    /**
     * List all discussions related to my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgDiscussions(q: String? = null): Flow<DgfrResource<List<Discussion>>>

    /**
     * List all issues related to my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgIssues(q: String? = null): Flow<DgfrResource<List<Issue>>>

    /**
     * List all reuses related to me and my organizations
     * @param q The string to filter items (optional)
     */
    suspend fun getMyOrgReuses(q: String? = null): Flow<DgfrResource<List<Reuse>>>

    /**
     * List all my reuses (including private ones)
     */
    suspend fun getMyReuses(): Flow<DgfrResource<List<Reuse>>>

}

