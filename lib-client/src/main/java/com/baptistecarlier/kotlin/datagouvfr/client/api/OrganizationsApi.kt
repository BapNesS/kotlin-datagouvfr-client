package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.coroutines.flow.Flow

/**
 * Organization related operations
 */
interface OrganizationsApi : WithApiKey {

    /**
     * List or search all organizations
     * @param q The search query (optional)
     * @param facets Selected facets to fetch (optional)
     * @param reuses (optional)
     * @param badge (optional)
     * @param datasets (optional)
     * @param followers (optional)
     * @param sort The field (and direction) on which sorting apply (optional)
     * @param page The page to display (optional, default to 0)
     * @param pageSize The page size (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListOrganizations(
        q: String? = null,
        facets: List<String>? = null,
        reuses: String? = null,
        badge: String? = null,
        datasets: String? = null,
        followers: String? = null,
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrCallState<OrganizationPage>>

    /**
     * Create a new organization
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun postCreateOrganization(
        payload: Organization
    ): Flow<DgfrCallState<Organization>>

    /**
     * List all available organization badges and their labels
     */
    fun getAvailableOrganizationBadges(): Flow<DgfrCallState<Map<String, String>>>

    /**
     * List all possible organization roles
     */
    fun getOrgRoles(): Flow<DgfrCallState<List<OrganizationRole>>>

    /**
     * Suggest organizations
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    fun getSuggestOrganizations(
        q: String,
        size: Int? = null
    ): Flow<DgfrCallState<List<OrganizationSuggestion>>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    fun deleteUnfollowOrganization(id: String): Flow<DgfrCallState<Boolean>>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListOrganizationFollowers(
        id: String,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrCallState<FollowPage>>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    fun postFollowOrganization(
        id: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Delete a organization given its identifier
     * @param org The organization ID or slug (required)
     */
    fun deleteOrganization(
        org: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Get a organization given its identifier
     * @param org The organization ID or slug (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getOrganization(
        org: String
    ): Flow<DgfrCallState<Organization>>

    /**
     * Update a organization given its identifier
     * @param org The organization ID or slug (required)
     * @param payload (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun putUpdateOrganization(
        org: String,
        payload: Organization
    ): Flow<DgfrCallState<Organization>>

    /**
     * Create a new badge for a given organization
     * @param org The organization ID or slug (required)
     * @param payload (required)
     */
    fun postAddOrganizationBadge(
        org: String,
        payload: Badge
    ): Flow<DgfrCallState<Badge>>

    /**
     * Delete a badge for a given organization
     * @param badgeKind (required)
     * @param org The organization ID or slug (required)
     */
    fun deleteOrganizationBadge(
        badgeKind: String,
        org: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * @param org The organization ID or slug (required)
     */
    fun getRdfOrganization(org: String): Flow<DgfrCallState<String>>

    /**
     * @param org The organization ID or slug (required)
     * @param format (required)
     */
    fun getRdfOrganizationFormat(
        org: String,
        format: String
    ): Flow<DgfrCallState<String>>

    /**
     * List organization datasets (including private ones when member)
     * @param org (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param sort The sorting attribute (optional, default to -created)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListOrganizationDatasets(
        org: String,
        page: Int? = null,
        pageSize: Int? = null,
        sort: String? = null
    ): Flow<DgfrCallState<DatasetPage>>

    /**
     * List organization discussions
     * @param org (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListOrganizationDiscussions(org: String): Flow<DgfrCallState<List<Discussion>>>

    /**
     * List organization issues
     * @param org (required)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListOrganizationIssues(org: String): Flow<DgfrCallState<List<Issue>>>

    /**
     * Upload a new logo
     * @param org The organization ID or slug (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    fun postOrganizationLogo(
        org: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>>

    /**
     * Set the logo BBox
     * @param org The organization ID or slug (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    fun putResizeOrganizationLogo(
        org: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>>

    /**
     * Delete member from an organization
     * @param org The organization ID or slug (required)
     * @param user (required)
     */
    fun deleteOrganizationMember(
        org: String,
        user: String
    ): Flow<DgfrCallState<Boolean>>

    /**
     * Add a member into a given organization
     * @param org The organization ID or slug (required)
     * @param user (required)
     * @param payload (required)
     */
    fun postCreateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrCallState<Member>>

    /**
     * Update member status into a given organization
     * @param org The organization ID or slug (required)
     * @param user (required)
     * @param payload (required)
     */
    fun putUpdateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrCallState<Member>>

    /**
     * List membership requests for a given organization
     * @param org The organization ID or slug (required)
     * @param status If provided, only return requests ith a given status (optional)
     */
    fun getListMembershipRequests(
        org: String,
        status: String? = null
    ): Flow<DgfrCallState<List<MembershipRequest>>>

    /**
     * Apply for membership to a given organization
     * @param org The organization ID or slug (required)
     */
    fun postMembershipRequest(org: String): Flow<DgfrCallState<MembershipRequest>>

    /**
     * Accept user membership to a given organization
     * @param id (required)
     * @param org The organization ID or slug (required)
     */
    fun postAcceptMembership(
        id: String,
        org: String
    ): Flow<DgfrCallState<Member>>

    /**
     * Refuse user membership to a given organization
     * @param id (required)
     * @param org The organization ID or slug (required)
     * @param payload (required)
     */
    fun postRefuseMembership(
        id: String,
        org: String,
        payload: RefuseMembership
    ): Flow<DgfrCallState<Boolean>>

    /**
     * List organization reuses (including private ones when member)
     * @param org (required)
     */
    fun getListOrganizationReuses(
        org: String
    ): Flow<DgfrCallState<List<Reuse>>>
}
