package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionStart
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Discussion related operations
 */
interface DiscussionsApi: WithApiKey {

    /**
     * List all Discussions
     * @param sort The sorting attribute (optional, default to -created)
     * @param closed Filters discussions on their closed status if specified (optional)
     * @param forIds Filter discussions for a given subject (optional)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getListDiscussions(
        sort: String? = null,
        closed: Boolean? = null,
        forIds: List<String>? = null,
        page: Int? = null,
        pageSize: Int? = null,
    ): Flow<DgfrResource<DiscussionPage>>

    /**
     * Create a new Discussion
     * @param payload (required)
     */
    suspend fun postCreateDiscussion(
        payload: DiscussionStart,
    ): Flow<DgfrResource<Discussion>>

    /**
     * Delete a discussion given its ID
     * @param id (required)
     */
    suspend fun deleteDiscussion(
        id: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Get a discussion given its ID
     * @param id (required)
     */
    suspend fun getDiscussion(
        id: String,
    ): Flow<DgfrResource<Discussion>>

    /**
     * Add comment and optionally close a discussion given its ID
     * @param id (required)
     * @param payload (required)
     */
    suspend fun postCommentDiscussion(
        id: String,
        payload: DiscussionResponse,
    ): Flow<DgfrResource<Discussion>>

    /**
     * Delete a comment given its index
     * @param id (required)
     * @param cidx (required)
     */
    suspend fun deleteDiscussionComment(
        id: String,
        cidx: Int
    ): Flow<DgfrResource<Boolean>>
    
}