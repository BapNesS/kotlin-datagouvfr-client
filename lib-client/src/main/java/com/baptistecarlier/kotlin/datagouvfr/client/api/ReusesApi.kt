package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Reuse related operations
 */
interface ReusesApi: WithApiKey {

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
    suspend fun getListReuses(
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
    ): Flow<ReusePage?>

    /**
     * Create a new object
     * @param payload (required)
     */
    suspend fun postCreateReuse(
        payload: Reuse
    ): Flow<Reuse?>

    /**
     * List all available reuse badges and their labels
     * TODO : check the return content
     */
    suspend fun getAvailableReuseBadges(): Flow<Boolean?>

    /**
     * Suggest reuses
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    suspend fun getSuggestReuses(
        q: String,
        size: Int? = null
    ): Flow<List<ReuseSuggestion>?>

    /**
     * List all reuse types
     */
    suspend fun getReuseTypes(): Flow<List<ReuseType>?>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    suspend fun deleteUnfollowReuse(id: String): Flow<Boolean?>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getListReuseFollowers(
        id: String,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<FollowPage?>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    suspend fun postFollowReuse(id: String): Flow<Boolean?>

    /**
     * Delete a given reuse
     * @param reuse The reuse ID or slug (required)
     */
    suspend fun deleteReuse(reuse: String): Flow<Boolean?>

    /**
     * Fetch a given reuse
     * @param reuse The reuse ID or slug (required)
     */
    suspend fun getReuse(
        reuse: String
    ): Flow<Reuse?>

    /**
     * Update a given reuse
     * @param reuse The reuse ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateReuse(
        reuse: String,
        payload: Reuse
    ): Flow<Reuse?>

    /**
     * Create a new badge for a given reuse
     * @param reuse The reuse ID or slug (required)
     * @param payload (required)
     */
    suspend fun postAddReuseBadge(
        reuse: String,
        payload: Badge
    ): Flow<Badge?>

    /**
     * Delete a badge for a given reuse
     * @param badgeKind (required)
     * @param reuse The reuse ID or slug (required)
     */
    suspend fun deleteReuseBadge(
        badgeKind: String,
        reuse: String
    ): Flow<Boolean?>

    /**
     * Add a dataset to a given reuse
     * @param reuse The reuse ID or slug (required)
     * @param payload (required)
     */
    suspend fun postReuseAddDataset(
        reuse: String,
        payload: DatasetReference
    ): Flow<Reuse?>
    /**
     * Unmark a reuse as featured
     * @param reuse The reuse ID or slug (required)
     */
    suspend fun deleteUnfeatureReuse(
        reuse: String
    ): Flow<Reuse?>

    /**
     * Mark a reuse as featured
     * @param reuse The reuse ID or slug (required)
     */
    suspend fun postFeatureReuse(
        reuse: String
    ): Flow<Reuse?>

}