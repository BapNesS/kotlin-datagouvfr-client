package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Organization related operations
 */
interface OrganizationsApi: WithApiKey {

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
    suspend fun getListOrganizations(
        q: String? = null,
        /*facets: List<String>? = null,*/
        reuses: String? = null,
        badge: String? = null,
        datasets: String? = null,
        followers: String? = null,
        sort: String? = null,
        page: Int? = null,
        pageSize: Int? = null): Flow<DgfrResource<OrganizationPage>>

    /**
     * Create a new organization
     * @param payload (required)
     */
    suspend fun postCreateOrganization(
        payload: Organization
        ): Flow<DgfrResource<Organization>>

    /**
     * List all available organization badges and their labels
     */
    suspend fun getAvailableOrganizationBadges(): Flow<DgfrResource<Map<String, String>>>

    /**
     * List all possible organization roles
     */
    suspend fun getOrgRoles(): Flow<DgfrResource<List<OrganizationRole>>>

    /**
     * Suggest organizations
     * @param q The string to autocomplete/suggest (required)
     * @param size The amount of suggestion to fetch (optional, default to 10)
     */
    suspend fun getSuggestOrganizations(
        q: String,
        size: Int? = null): Flow<DgfrResource<List<OrganizationSuggestion>>>

    /**
     * Unfollow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    suspend fun deleteUnfollowOrganization(id: String): Flow<DgfrResource<Boolean>>

    /**
     * List all followers for a given object
     * @param id (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getListOrganizationFollowers(
        id: String,
        page: Int? = null,
        pageSize: Int? = null
    ): Flow<DgfrResource<FollowPage>>

    /**
     * Follow an object given its ID
     * Returns the number of followers left after the operation
     * @param id (required)
     */
    suspend fun postFollowOrganization(
        id: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Delete a organization given its identifier
     * @param org The organization ID or slug (required)
     */
    suspend fun deleteOrganization(
        org: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Get a organization given its identifier
     * @param org The organization ID or slug (required)
     */
    suspend fun getOrganization(
        org: String
    ): Flow<DgfrResource<Organization>>

    /**
     * Update a organization given its identifier
     * @param org The organization ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateOrganization(
        org: String,
        payload: Organization
    ): Flow<DgfrResource<Organization>>

    /**
     * Create a new badge for a given organization
     * @param org The organization ID or slug (required)
     * @param payload (required)
     */
    suspend fun postAddOrganizationBadge(
        org: String,
        payload: Badge
        ): Flow<DgfrResource<Badge>>

    /**
     * Delete a badge for a given organization
     * @param badgeKind (required)
     * @param org The organization ID or slug (required)
     */
    suspend fun deleteOrganizationBadge(
        badgeKind: String,
        org: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * @param org The organization ID or slug (required)
     */
    suspend fun getRdfOrganization(org: String): Flow<DgfrResource<String>>

    /**
     * @param org The organization ID or slug (required)
     * @param format (required)
     */
    suspend fun getRdfOrganizationFormat(
        org: String,
        format: String
    ): Flow<DgfrResource<String>>

    /**
     * List organization datasets (including private ones when member)
     * @param org (required)
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param sort The sorting attribute (optional, default to -created)
     */
    suspend fun getListOrganizationDatasets(
        org: String,
        page: Int? = null,
        pageSize: Int? = null,
        sort: String? = null
    ): Flow<DgfrResource<DatasetPage>>

    /**
     * List organization discussions
     * @param org (required)
     */
    suspend fun getListOrganizationDiscussions(org: String): Flow<DgfrResource<List<Discussion>>>

    /**
     * List organization issues
     * @param org (required)
     */
    suspend fun getListOrganizationIssues(org: String): Flow<DgfrResource<List<Issue>>>

    /**
     * Upload a new logo
     * @param org The organization ID or slug (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    suspend fun postOrganizationLogo(
        org: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>>

    /**
     * Set the logo BBox
     * @param org The organization ID or slug (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    suspend fun putResizeOrganizationLogo(
        org: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>>

    /**
     * Delete member from an organization
     * @param org The organization ID or slug (required)
     * @param user (required)
     */
    suspend fun deleteOrganizationMember(
        org: String,
        user: String
    ): Flow<DgfrResource<Boolean>>

    /**
     * Add a member into a given organization
     * @param org The organization ID or slug (required)
     * @param user (required)
     * @param payload (required)
     */
    suspend fun postCreateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrResource<Member>>

    /**
     * Update member status into a given organization
     * @param org The organization ID or slug (required)
     * @param user (required)
     * @param payload (required)
     */
    suspend fun putUpdateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrResource<Member>>

    /**
     * List membership requests for a given organization
     * @param org The organization ID or slug (required)
     * @param status If provided, only return requests ith a given status (optional)
     */
    suspend fun getListMembershipRequests(
        org: String,
        status: String? = null
    ): Flow<DgfrResource<List<MembershipRequest>>>

    /**
     * Apply for membership to a given organization
     * @param org The organization ID or slug (required)
     */
    suspend fun postMembershipRequest(org: String): Flow<DgfrResource<MembershipRequest>>

    /**
     * Accept user membership to a given organization
     * @param id (required)
     * @param org The organization ID or slug (required)
     */
    suspend fun postAcceptMembership(
        id: String,
        org: String
    ): Flow<DgfrResource<Member>>

    /**
     * Refuse user membership to a given organization
     * @param id (required)
     * @param org The organization ID or slug (required)
     * @param payload (required)
     */
    suspend fun postRefuseMembership(
        id: String,
        org: String,
        payload: RefuseMembership
    ): Flow<DgfrResource<Boolean>>

    /**
     * List organization reuses (including private ones when member)
     * @param org (required)
     */
    suspend fun getListOrganizationReuses(
        org: String
    ): Flow<DgfrResource<List<Reuse>>>

}