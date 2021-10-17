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
    fun deleteMe(): Flow<DgfrResource<Boolean>>

    /**
     * Fetch the current user (me) identity
     */
    fun getMe(): Flow<DgfrResource<Me>>

    /**
     * Update my profile
     * @param payload (required)
     */
    fun putUpdateMe(payload: Me): Flow<DgfrResource<Me>>

    /**
     * Clear/destroy an apikey
     */
    fun deleteClearApikey(): Flow<DgfrResource<Boolean>>

    /**
     * (Re)Generate my API Key
     */
    fun postGenerateApikey(): Flow<DgfrResource<ApiKey>>

    /**
     * Upload a new avatar
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    fun postMyAvatar(
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>>

    /**
     * List all my datasets (including private ones)
     */
    fun getMyDatasets(): Flow<DgfrResource<List<Dataset>>>

    /**
     * Fetch the current user (me) metrics
     */
    fun getMyMetrics(): Flow<DgfrResource<List<MyMetrics>>>

    /**
     * List all community resources related to me and my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgCommunityResources(q: String? = null): Flow<DgfrResource<List<CommunityResource>>>

    /**
     * List all datasets related to me and my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgDatasets(q: String? = null): Flow<DgfrResource<List<Dataset>>>

    /**
     * List all discussions related to my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgDiscussions(q: String? = null): Flow<DgfrResource<List<Discussion>>>

    /**
     * List all issues related to my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgIssues(q: String? = null): Flow<DgfrResource<List<Issue>>>

    /**
     * List all reuses related to me and my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgReuses(q: String? = null): Flow<DgfrResource<List<Reuse>>>

    /**
     * List all my reuses (including private ones)
     */
    fun getMyReuses(): Flow<DgfrResource<List<Reuse>>>

}

