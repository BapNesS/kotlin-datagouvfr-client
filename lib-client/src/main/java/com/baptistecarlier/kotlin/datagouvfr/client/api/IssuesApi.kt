package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.Issue
import com.baptistecarlier.kotlin.datagouvfr.client.model.IssuePage
import com.baptistecarlier.kotlin.datagouvfr.client.model.IssueResponse
import kotlinx.coroutines.flow.Flow

/**
 * Issue related operations
 */
internal interface IssuesApi : WithApiKey {

    /**
     * List all Issues
     * @param sort The sorting attribute (optional, default to -created)
     * @param closed Filter closed issues. Filters issues on their closed status if specified (optional)
     * @param forIds Filter issues for a given subject (optional)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListIssues(
        sort: String? = null,
        closed: Boolean? = null,
        forIds: List<String>? = null,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrCallState<IssuePage>>

    /**
     * Create a new Issue
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateIssue(
        payload: Issue
    ): Flow<DgfrCallState<Issue>>

    /**
     * Get an issue given its ID
     * @param id (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getIssue(
        id: String
    ): Flow<DgfrCallState<Issue>>

    /**
     * Add comment and optionally close an issue given its ID
     * @param id (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCommentIssue(
        id: String,
        payload: IssueResponse
    ): Flow<DgfrCallState<Issue>>
}
