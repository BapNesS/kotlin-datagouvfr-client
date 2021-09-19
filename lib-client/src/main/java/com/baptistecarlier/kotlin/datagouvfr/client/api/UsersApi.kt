package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * User related operations
 */
interface UsersApi: WithApiKey {

    /**
     * List all users
     * @param q The search query (optional)
     * @param facets Selected facets to fetch (optional)
     * @param organization (optional)
     * @param datasets (optional)
     * @param followers (optional)
     * @param sort The field (and direction) on which sorting apply (optional)
     * @param page The page to display (optional, default to 0)
     * @param pageSize The page size (optional, default to 20)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun getListUsers(
        q: String?,
        facets: List<String>?,
        organization: String?,
        datasets: String?,
        followers: String?,
        sort: String?,
        page: Int?,
        pageSize: Int?,
    ): Flow<UserPage?>

    /**
     * Create a new object
     * @param payload (required)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun postCreateUser(payload: User): Flow<User?>

    /**
     * List all possible user roles
     * @param xFields An optional fields mask (optional)
     */
    suspend fun getUserRoles(): Flow<List<UserRole>?>

    /**
     * Suggest users
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun getSuggestUsers(q: String, size: Int?): Flow<List<UserSuggestion>?>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    suspend fun deleteUnfollowUser(id: String): Flow<Boolean?>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun getListUserFollowers(id: String, page: Int?, pageSize: Int?): Flow<FollowPage?>

    /**
     * Follow a user given its ID
     * @param id (required)
     */
    suspend fun postFollowUser(id: String): Flow<Boolean?>

    /**
     * Delete a user given its identifier
     * @param user (required)
     */
    suspend fun deleteUser(user: String): Flow<Boolean?>

    /**
     * Get a user given its identifier
     * @param user (required)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun getUser(user: String): Flow<User?>

    /**
     * Update a user given its identifier
     * @param user (required)
     * @param payload (required)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun putUpdateUser(user: String, payload: User): Flow<User?>

    /**
     * Upload a new avatar for a given user
     * @param user (required)
     * @param file (optional)
     * @param bbox (optional)
     * @param xFields An optional fields mask (optional)
     */
    suspend fun postUserAvatar(user: String, bbox: String?): Flow<UploadedImage?>

}