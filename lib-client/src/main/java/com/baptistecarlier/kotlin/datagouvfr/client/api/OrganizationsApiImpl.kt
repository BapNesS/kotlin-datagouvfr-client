package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.readAndClose
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class OrganizationsApiImpl(private val client: HttpClient) : OrganizationsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    override fun getListOrganizations(
        q: String?,
        /*facets: List<String>?,*/
        reuses: String?,
        badge: String?,
        datasets: String?,
        followers: String?,
        sort: String?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<OrganizationPage>> = loadingFlow {
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

    @OptIn(MissingFieldMapping::class)
    override fun postCreateOrganization(payload: Organization): Flow<DgfrCallState<Organization>> = loadingFlow {
        client.post(
            path = "organizations/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getAvailableOrganizationBadges(): Flow<DgfrCallState<Map<String, String>>> = loadingFlow {
        client.get(
            path = "organizations/badges/"
        )
    }

    override fun getOrgRoles(): Flow<DgfrCallState<List<OrganizationRole>>> = loadingFlow {
        client.get(
            path = "organizations/roles/"
        )
    }

    override fun getSuggestOrganizations(
        q: String,
        size: Int?
    ): Flow<DgfrCallState<List<OrganizationSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "organizations/suggest/?${builder.urlEncore()}"
        )
    }

    override fun deleteUnfollowOrganization(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListOrganizationFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<FollowPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "organizations/$id/followers/?${builder.urlEncore()}"
        )
    }

    override fun postFollowOrganization(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "organizations/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun deleteOrganization(org: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$org/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getOrganization(org: String): Flow<DgfrCallState<Organization>> = loadingFlow {
        client.get(
            path = "organizations/$org/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateOrganization(
        org: String,
        payload: Organization
    ): Flow<DgfrCallState<Organization>> = loadingFlow {
        client.put(
            path = "organizations/$org/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun postAddOrganizationBadge(org: String, payload: Badge): Flow<DgfrCallState<Badge>> = loadingFlow {
        client.post(
            path = "organizations/$org/badges/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteOrganizationBadge(badgeKind: String, org: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$org/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getRdfOrganization(org: String): Flow<DgfrCallState<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "organizations/$org/catalog"
        )
        response.content.readAndClose().orEmpty()
    }

    override fun getRdfOrganizationFormat(org: String, format: String): Flow<DgfrCallState<String>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "organizations/$org/catalog.$format"
        )
        response.content.readAndClose().orEmpty()
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListOrganizationDatasets(
        org: String,
        page: Int?,
        pageSize: Int?,
        sort: String?
    ): Flow<DgfrCallState<DatasetPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "organizations/$org/datasets/?${builder.urlEncore()}"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListOrganizationDiscussions(org: String): Flow<DgfrCallState<List<Discussion>>> = loadingFlow {
        client.get(
            path = "organizations/$org/discussions/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListOrganizationIssues(org: String): Flow<DgfrCallState<List<Issue>>> = loadingFlow {
        client.get(
            path = "organizations/$org/issues/"
        )
    }

    override fun postOrganizationLogo(
        org: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "organizations/$org/logo",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

    override fun putResizeOrganizationLogo(
        org: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "organizations/$org/logo",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
            }
        ) {
            method = HttpMethod.Put
            header("X-API-KEY", apiKey)
        }
    }

    override fun deleteOrganizationMember(org: String, user: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "organizations/$org/member/$user"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun postCreateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrCallState<Member>> = loadingFlow {
        client.post(
            path = "organizations/$org/member/$user"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun putUpdateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<DgfrCallState<Member>> = loadingFlow {
        client.put(
            path = "organizations/$org/member/$user"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getListMembershipRequests(
        org: String,
        status: String?
    ): Flow<DgfrCallState<List<MembershipRequest>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("status", status)

        client.get(
            path = "organizations/$org/membership/?${builder.urlEncore()}"
        )
    }

    // A bit light, isn't ?
    override fun postMembershipRequest(
        org: String
    ): Flow<DgfrCallState<MembershipRequest>> = loadingFlow {
        client.post(
            path = "organizations/$org/membership/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postAcceptMembership(id: String, org: String): Flow<DgfrCallState<Member>> = loadingFlow {
        client.post(
            path = "organizations/$org/membership/$id/accept/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postRefuseMembership(
        id: String,
        org: String,
        payload: RefuseMembership
    ): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "organizations/$org/membership/$id/refuse/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getListOrganizationReuses(org: String): Flow<DgfrCallState<List<Reuse>>> = loadingFlow {
        client.get(
            path = "organizations/$org/reuses/"
        )
    }
}
