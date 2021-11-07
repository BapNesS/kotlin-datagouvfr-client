package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * User related operations
 */
internal interface UsersApi : WithApiKey {

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
    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    fun getListUsers(
        q: String?,
        /*facets: List<String>?,*/
        organization: String?,
        datasets: String?,
        followers: String?,
        sort: String?,
        page: Int?,
        pageSize: Int?,
    ): Flow<DgfrCallState<UserPage>>

    /**
     * Create a new object
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateUser(payload: User): Flow<DgfrCallState<User>>

    /**
     * List all possible user roles
     */
    fun getUserRoles(): Flow<DgfrCallState<List<UserRole>>>

    /**
     * Suggest users
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestUsers(q: String, size: Int?): Flow<DgfrCallState<List<UserSuggestion>>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    fun deleteUnfollowUser(id: String): Flow<DgfrCallState<Boolean>>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListUserFollowers(id: String, page: Int?, pageSize: Int?): Flow<DgfrCallState<FollowPage>>

    /**
     * Follow a user given its ID
     * @param id (required)
     */
    fun postFollowUser(id: String): Flow<DgfrCallState<Boolean>>

    /**
     * Delete a user given its identifier
     * @param user (required)
     */
    fun deleteUser(user: String): Flow<DgfrCallState<Boolean>>

    /**
     * Get a user given its identifier
     * @param user (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getUser(user: String): Flow<DgfrCallState<User>>

    /**
     * Update a user given its identifier
     * @param user (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun putUpdateUser(user: String, payload: User): Flow<DgfrCallState<User>>

    /**
     * Upload a new avatar for a given user
     * @param user (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    fun postUserAvatar(
        user: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>>
}
