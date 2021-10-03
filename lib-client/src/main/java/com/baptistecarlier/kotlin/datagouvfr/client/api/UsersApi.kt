package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
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
    ): Flow<DgfrResource<UserPage>>

    /**
     * Create a new object
     * @param payload (required)
     */
    suspend fun postCreateUser(payload: User): Flow<DgfrResource<User>>

    /**
     * List all possible user roles
     */
    suspend fun getUserRoles(): Flow<DgfrResource<List<UserRole>>>

    /**
     * Suggest users
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    suspend fun getSuggestUsers(q: String, size: Int?): Flow<DgfrResource<List<UserSuggestion>>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    suspend fun deleteUnfollowUser(id: String): Flow<DgfrResource<Boolean>>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getListUserFollowers(id: String, page: Int?, pageSize: Int?): Flow<DgfrResource<FollowPage>>

    /**
     * Follow a user given its ID
     * @param id (required)
     */
    suspend fun postFollowUser(id: String): Flow<DgfrResource<Boolean>>

    /**
     * Delete a user given its identifier
     * @param user (required)
     */
    suspend fun deleteUser(user: String): Flow<DgfrResource<Boolean>>

    /**
     * Get a user given its identifier
     * @param user (required)
     */
    suspend fun getUser(user: String): Flow<DgfrResource<User>>

    /**
     * Update a user given its identifier
     * @param user (required)
     * @param payload (required)
     */
    suspend fun putUpdateUser(user: String, payload: User): Flow<DgfrResource<User>>

    /**
     * Upload a new avatar for a given user
     * @param user (required)
     * @param file (optional)
     * @param bbox (optional)
     */
    /*suspend fun postUserAvatar(user: String, bbox: String?): Flow<DgfrResource<UploadedImage>>*/

}