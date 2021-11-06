package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionStart
import kotlinx.coroutines.flow.Flow

/**
 * Discussion related operations
 */
internal interface DiscussionsApi : WithApiKey {

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
    ): Flow<DgfrCallState<DiscussionPage>>

    /**
     * Create a new Discussion
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateDiscussion(
        payload: DiscussionStart,
    ): Flow<DgfrCallState<Discussion>>

    /**
     * Delete a discussion given its ID
     * @param id (required)
     */
    fun deleteDiscussion(
        id: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Get a discussion given its ID
     * @param id (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getDiscussion(
        id: String
    ): Flow<DgfrCallState<Discussion>>

    /**
     * Add comment and optionally close a discussion given its ID
     * @param id (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCommentDiscussion(
        id: String,
        payload: DiscussionResponse
    ): Flow<DgfrCallState<Discussion>>

    /**
     * Delete a comment given its index
     * @param id (required)
     * @param cidx (required)
     */
    fun deleteDiscussionComment(
        id: String,
        cidx: Int
    ): Flow<DgfrCallState<Boolean>>
}
