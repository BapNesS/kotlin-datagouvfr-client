package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Reuse related operations
 */
interface ReusesApi : WithApiKey {

    /**
     * @param q The search query (optional)
     * @param facets Selected facets to fetch (optional)
     * @param tag (optional)
     * @param organization (optional)
     * @param owner (optional)
     * @param dataset (optional)
     * @param type (optional)
     * @param datasets (optional)
     * @param followers (optional)
     * @param badge (optional)
     * @param featured (optional)
     * @param sort The field (and direction) on which sorting apply (optional)
     * @param page The page to display (optional, default to 0)
     * @param pageSize The page size (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    fun getListReuses(
        q: String? = null,
        /*facets: List<String>? = null,*/
        tag: String? = null,
        organization: String? = null,
        owner: String? = null,
        dataset: String? = null,
        type: String? = null,
        datasets: String? = null,
        followers: String? = null,
        badge: String? = null,
        featured: Boolean? = null,
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrCallState<ReusePage>>

    /**
     * Create a new object
     * @param payload (required)
     */
    fun postCreateReuse(
        payload: Reuse
    ): Flow<DgfrCallState<Reuse>>

    /**
     * List all available reuse badges and their labels
     * TODO : check the return content
     */
    fun getAvailableReuseBadges(): Flow<DgfrCallState<Boolean>>

    /**
     * Suggest reuses
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestReuses(
        q: String,
        size: Int? = null
    ): Flow<DgfrCallState<List<ReuseSuggestion>>>

    /**
     * List all reuse types
     */
    fun getReuseTypes(): Flow<DgfrCallState<List<ReuseType>>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    fun deleteUnfollowReuse(id: String): Flow<DgfrCallState<Boolean>>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListReuseFollowers(
        id: String,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrCallState<FollowPage>>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    fun postFollowReuse(id: String): Flow<DgfrCallState<Boolean>>

    /**
     * Delete a given reuse
     * @param reuse The reuse ID or slug (required)
     */
    fun deleteReuse(reuse: String): Flow<DgfrCallState<Boolean>>

    /**
     * Fetch a given reuse
     * @param reuse The reuse ID or slug (required)
     */
    fun getReuse(
        reuse: String
    ): Flow<DgfrCallState<Reuse>>

    /**
     * Update a given reuse
     * @param reuse The reuse ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateReuse(
        reuse: String,
        payload: Reuse
    ): Flow<DgfrCallState<Reuse>>

    /**
     * Create a new badge for a given reuse
     * @param reuse The reuse ID or slug (required)
     * @param payload (required)
     */
    fun postAddReuseBadge(
        reuse: String,
        payload: Badge
    ): Flow<DgfrCallState<Badge>>

    /**
     * Delete a badge for a given reuse
     * @param badgeKind (required)
     * @param reuse The reuse ID or slug (required)
     */
    fun deleteReuseBadge(
        badgeKind: String,
        reuse: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Add a dataset to a given reuse
     * @param reuse The reuse ID or slug (required)
     * @param payload (required)
     */
    fun postReuseAddDataset(
        reuse: String,
        payload: DatasetReference
    ): Flow<DgfrCallState<Reuse>>
    /**
     * Unmark a reuse as featured
     * @param reuse The reuse ID or slug (required)
     */
    fun deleteUnfeatureReuse(
        reuse: String
    ): Flow<DgfrCallState<Reuse>>

    /**
     * Mark a reuse as featured
     * @param reuse The reuse ID or slug (required)
     */
    fun postFeatureReuse(
        reuse: String
    ): Flow<DgfrCallState<Reuse>>

    /**
     * Upload a new reuse image
     * @param reuse The reuse ID or slug (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    fun postReuseImage(
        reuse: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>>
}
