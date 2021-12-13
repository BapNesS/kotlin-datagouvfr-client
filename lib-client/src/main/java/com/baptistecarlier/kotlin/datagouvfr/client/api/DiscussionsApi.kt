package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionStart
import kotlinx.coroutines.flow.Flow

/**
 * Discussion related operations
 */
interface DiscussionsApi : WithApiKey {

    /**
     * List all Discussions
     * @param sort The sorting attribute (optional, default to -created)
     * @param closed Filters discussions on their closed status if specified (optional)
     * @param forIds Filter discussions for a given subject (optional)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListDiscussions(
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
    fun postCreateDiscussion(
        payload: DiscussionStart,
    ): Flow<DgfrResource<Discussion>>

    /**
     * Delete a discussion given its ID
     * @param id (required)
     */
    fun deleteDiscussion(
        id: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Get a discussion given its ID
     * @param id (required)
     */
    fun getDiscussion(
        id: String,
    ): Flow<DgfrResource<Discussion>>

    /**
     * Add comment and optionally close a discussion given its ID
     * @param id (required)
     * @param payload (required)
     */
    fun postCommentDiscussion(
        id: String,
        payload: DiscussionResponse,
    ): Flow<DgfrResource<Discussion>>

    /**
     * Delete a comment given its index
     * @param id (required)
     * @param cidx (required)
     */
    fun deleteDiscussionComment(
        id: String,
        cidx: Int
    ): Flow<DgfrResource<Boolean>>
}
