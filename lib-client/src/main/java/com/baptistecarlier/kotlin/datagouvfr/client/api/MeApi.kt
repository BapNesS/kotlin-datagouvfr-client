package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Connected user related operations
 */
interface MeApi : WithApiKey {

    /**
     * Delete my profile
     */
    fun deleteMe(): Flow<DgfrCallState<Boolean>>

    /**
     * Fetch the current user (me) identity
     */
    fun getMe(): Flow<DgfrCallState<Me>>

    /**
     * Update my profile
     * @param payload (required)
     */
    fun putUpdateMe(payload: Me): Flow<DgfrCallState<Me>>

    /**
     * Clear/destroy an apikey
     */
    fun deleteClearApikey(): Flow<DgfrCallState<Boolean>>

    /**
     * (Re)Generate my API Key
     */
    fun postGenerateApikey(): Flow<DgfrCallState<ApiKey>>

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
    ): Flow<DgfrCallState<UploadedImage>>

    /**
     * List all my datasets (including private ones)
     */
    fun getMyDatasets(): Flow<DgfrCallState<List<Dataset>>>

    /**
     * Fetch the current user (me) metrics
     */
    fun getMyMetrics(): Flow<DgfrCallState<List<MyMetrics>>>

    /**
     * List all community resources related to me and my organizations
     * @param q The string to filter items (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun getMyOrgCommunityResources(q: String? = null): Flow<DgfrCallState<List<CommunityResource>>>

    /**
     * List all datasets related to me and my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgDatasets(q: String? = null): Flow<DgfrCallState<List<Dataset>>>

    /**
     * List all discussions related to my organizations
     * @param q The string to filter items (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun getMyOrgDiscussions(q: String? = null): Flow<DgfrCallState<List<Discussion>>>

    /**
     * List all issues related to my organizations
     * @param q The string to filter items (optional)
     */
    @OptIn(MissingFieldMapping::class)
    fun getMyOrgIssues(q: String? = null): Flow<DgfrCallState<List<Issue>>>

    /**
     * List all reuses related to me and my organizations
     * @param q The string to filter items (optional)
     */
    fun getMyOrgReuses(q: String? = null): Flow<DgfrCallState<List<Reuse>>>

    /**
     * List all my reuses (including private ones)
     */
    fun getMyReuses(): Flow<DgfrCallState<List<Reuse>>>
}
