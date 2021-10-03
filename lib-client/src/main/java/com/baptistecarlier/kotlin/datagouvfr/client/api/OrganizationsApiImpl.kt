package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSucces
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.readAndClose
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class OrganizationsApiImpl(private val client: HttpClient) : OrganizationsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListOrganizations(
        q: String?,
        /*facets: List<String>?,*/ // TODO
        reuses: String?,
        badge: String?,
        datasets: String?,
        followers: String?,
        sort: String?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<OrganizationPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("reuses", reuses)
        builder.appendIfNotNull("badge", badge)
        builder.appendIfNotNull("datasets", datasets)
        builder.appendIfNotNull("followers", followers)
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "organizations/?${builder.urlEncore()}"
        )
    }

    override suspend fun postCreateOrganization(payload: Organization): Flow<DgfrResource<Organization>> = loadingFlow {
        client.post(
            path = "organizations/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun getAvailableOrganizationBadges(): Flow<DgfrResource<Map<String, String>>> = loadingFlow {
        client.get(
            path = "organizations/badges/"
        )
    }

    override suspend fun getOrgRoles(): Flow<DgfrResource<List<OrganizationRole>>> = loadingFlow {
        client.get(
            path = "organizations/roles/"
        )
    }

    override suspend fun getSuggestOrganizations(
        q: String,
        size: Int?
    ): Flow<DgfrResource<List<OrganizationSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "organizations/suggest/?${builder.urlEncore()}"
        )
    }

    override suspend fun deleteUnfollowOrganization(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getListOrganizationFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<FollowPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "organizations/$id/followers/?${builder.urlEncore()}"
        )
    }

    override suspend fun postFollowOrganization(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "organizations/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun deleteOrganization(org: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$org/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getOrganization(org: String): Flow<DgfrResource<Organization>> = loadingFlow {
        client.get(
            path = "organizations/$org/"
        )
    }

    override suspend fun putUpdateOrganization(
        org: String,
        payload: Organization
    ): Flow<DgfrResource<Organization>> = loadingFlow {
        client.put(
            path = "organizations/$org/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun postAddOrganizationBadge(org: String, payload: Badge): Flow<DgfrResource<Badge>> = loadingFlow {
        client.post(
            path = "organizations/$org/badges/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteOrganizationBadge(badgeKind: String, org: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$org/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getRdfOrganization(org: String): Flow<DgfrResource<String>> = loadingFlow {
         val response = client.get<HttpResponse>(
            path = "organizations/$org/catalog"
        )
         response.content.readAndClose().orEmpty()
    }

    override suspend fun getRdfOrganizationFormat(org: String, format: String): Flow<DgfrResource<String>> = loadingFlow {
         val response = client.get<HttpResponse>(
            path = "organizations/$org/catalog.$format"
        )
         response.content.readAndClose().orEmpty()
    }

    override suspend fun getListOrganizationDatasets(
        org: String,
        page: Int?,
        pageSize: Int?,
        sort: String?
    ): Flow<DgfrResource<DatasetPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "organizations/$org/datasets/?${builder.urlEncore()}"
        )
    }

    override suspend fun getListOrganizationDiscussions(org: String): Flow<DgfrResource<List<Discussion>>> = loadingFlow {
        client.get(
            path = "organizations/$org/discussions/"
        )
    }

    override suspend fun getListOrganizationIssues(org: String): Flow<DgfrResource<List<Issue>>> = loadingFlow {
        client.get(
            path = "organizations/$org/issues/"
        )
    }

    /*override suspend fun postOrganizationLogo(org: String, bbox: String?): Flow<DgfrResource<UploadedImage?> {
        TODO("Not yet implemented")
    }*/

    /*override suspend fun putResizeOrganizationLogo(
        org: String,
        bbox: String?
    ): Flow<DgfrResource<UploadedImage?> {
        TODO("Not yet implemented")
    }*/

    override suspend fun deleteOrganizationMember(org: String, user: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$org/member/$user/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun postCreateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrResource<Member>> = loadingFlow {
        client.post(
            path = "organizations/$org/member/$user/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun putUpdateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrResource<Member>> = loadingFlow {
        client.put(
            path = "organizations/$org/member/$user/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun getListMembershipRequests(
        org: String,
        status: String?
    ): Flow<DgfrResource<List<MembershipRequest>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("status", status)

        client.get(
            path = "organizations/$org/membership/?${builder.urlEncore()}"
        )
    }

    override suspend fun postAcceptMembership(id: String, org: String): Flow<DgfrResource<Member>> = loadingFlow {
        client.post(
            path = "organizations/$org/membership/$id/accept/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun postRefuseMembership(
        id: String,
        org: String,
        payload: RefuseMembership
    ): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "organizations/$org/membership/$id/refuse/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getListOrganizationReuses(org: String): Flow<DgfrResource<List<Reuse>>> = loadingFlow {
        client.get(
            path = "organizations/$org/reuses/"
        )
    }

}