package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
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
import kotlinx.coroutines.flow.flow

class OrganizationsApiImpl(private val client: HttpClient) : OrganizationsApi {

    private val tag = "OrganizationsApiImpl"

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
    ): Flow<OrganizationPage?> = flow {
        Log.d(tag, "getListOrganizations / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("reuses", reuses)
            builder.appendIfNotNull("badge", badge)
            builder.appendIfNotNull("datasets", datasets)
            builder.appendIfNotNull("followers", followers)
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<OrganizationPage?>(
                path = "organizations/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListOrganizations / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateOrganization(payload: Organization): Flow<Organization?> = flow {
        Log.d(tag, "postCreateOrganization / begin")
        val value = try {
            val response = client.post<Organization>(
                path = "organizations/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getAvailableOrganizationBadges(): Flow<Map<String, String>?> = flow {
        Log.d(tag, "getAvailableOrganizationBadges / begin")
        val value = try {
            val response = client.get<Map<String, String>?>(
                path = "organizations/badges/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getAvailableOrganizationBadges / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getOrgRoles(): Flow<List<OrganizationRole>?> = flow {
        Log.d(tag, "getOrgRoles / begin")
        val value = try {
            val response = client.get<List<OrganizationRole>>(
                path = "organizations/roles/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getOrgRoles / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestOrganizations(
        q: String,
        size: Int?
    ): Flow<List<OrganizationSuggestion>?> = flow {
        Log.d(tag, "getSuggestOrganizations / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<OrganizationSuggestion>>(
                path = "organizations/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestOrganizations / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnfollowOrganization(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteUnfollowOrganization / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "organizations/$id/followers/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteUnfollowOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListOrganizationFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<FollowPage?> = flow {
        Log.d(tag, "getListOrganizationFollowers / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<FollowPage>(
                path = "organizations/$id/followers/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListOrganizationFollowers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postFollowOrganization(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "postFollowOrganization / begin")
        val value = try {
            val response = client.post<HttpResponse>(
                path = "organizations/$id/followers/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "postFollowOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteOrganization(org: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteOrganization / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "organizations/$org/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getOrganization(org: String): Flow<Organization?> = flow {
        Log.d(tag, "getOrganization / begin")
        val value = try {
            val response = client.get<Organization>(
                path = "organizations/$org/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateOrganization(
        org: String,
        payload: Organization
    ): Flow<Organization?> = flow {
        Log.d(tag, "putUpdateOrganization / begin")
        val value = try {
            val response = client.put<Organization>(
                path = "organizations/$org/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postAddOrganizationBadge(org: String, payload: Badge): Flow<Badge?> = flow {
        Log.d(tag, "postAddOrganizationBadge / begin")
        val value = try {
            val response = client.post<Badge>(
                path = "organizations/$org/badges/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postAddOrganizationBadge / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteOrganizationBadge(badgeKind: String, org: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteOrganizationBadge / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "organizations/$org/badges/$badgeKind/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteOrganizationBadge / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getRdfOrganization(org: String): Flow<String?> = flow {
        Log.d(tag, "getRdfOrganization / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "organizations/$org/catalog"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getRdfOrganization / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getRdfOrganizationFormat(org: String, format: String): Flow<String?> = flow {
        Log.d(tag, "getRdfOrganizationFormat / begin")
        val value = try {
            val response = client.get<HttpResponse?>(
                path = "organizations/$org/catalog.$format"
            )
            response?.content?.readAndClose()
        } catch (e: Exception) {
            Log.d(tag, "getRdfOrganizationFormat / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListOrganizationDatasets(
        org: String,
        page: Int?,
        pageSize: Int?,
        sort: String?
    ): Flow<DatasetPage?> = flow {
        Log.d(tag, "getListOrganizationDatasets / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<DatasetPage>(
                path = "organizations/$org/datasets/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListOrganizationDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListOrganizationDiscussions(org: String): Flow<List<Discussion>?> = flow {
        Log.d(tag, "getListOrganizationDiscussions / begin")
        val value = try {
            val response = client.get<List<Discussion>>(
                path = "organizations/$org/discussions/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListOrganizationDiscussions / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListOrganizationIssues(org: String): Flow<List<Issue>?> = flow {
        Log.d(tag, "getListOrganizationIssues / begin")
        val value = try {
            val response = client.get<List<Issue>>(
                path = "organizations/$org/issues/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListOrganizationIssues / Exception =  $e")
            null
        }
        emit(value)
    }

    /*override suspend fun postOrganizationLogo(org: String, bbox: String?): Flow<UploadedImage?> {
        TODO("Not yet implemented")
    }*/

    /*override suspend fun putResizeOrganizationLogo(
        org: String,
        bbox: String?
    ): Flow<UploadedImage?> {
        TODO("Not yet implemented")
    }*/

    override suspend fun deleteOrganizationMember(org: String, user: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteOrganizationMember / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "organizations/$org/member/$user/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteOrganizationMember / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<Member?> = flow {
        Log.d(tag, "postCreateOrganizationMember / begin")
        val value = try {
            val response = client.post<Member>(
                path = "organizations/$org/member/$user/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateOrganizationMember / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateOrganizationMember(
        org: String,
        user: String,
        payload: Member
    ): Flow<Member?> = flow {
        Log.d(tag, "putUpdateOrganizationMember / begin")
        val value = try {
            val response = client.put<Member>(
                path = "organizations/$org/member/$user/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateOrganizationMember / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListMembershipRequests(
        org: String,
        status: String?
    ): Flow<List<MembershipRequest>?> = flow {
        Log.d(tag, "getListMembershipRequests / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("status", status)

            val response = client.get<List<MembershipRequest>>(
                path = "organizations/$org/membership/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListMembershipRequests / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postAcceptMembership(id: String, org: String): Flow<Member?> = flow {
        Log.d(tag, "postAcceptMembership / begin")
        val value = try {
            val response = client.post<Member>(
                path = "organizations/$org/membership/$id/accept/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postAcceptMembership / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postRefuseMembership(
        id: String,
        org: String,
        payload: RefuseMembership
    ): Flow<Boolean?> = flow {
        Log.d(tag, "postRefuseMembership / begin")
        val value = try {
            val response = client.post<HttpResponse>(
                path = "organizations/$org/membership/$id/refuse/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "postRefuseMembership / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListOrganizationReuses(org: String): Flow<List<Reuse>?> = flow {
        Log.d(tag, "getListOrganizationReuses / begin")
        val value = try {
            val response = client.get<List<Reuse>>(
                path = "organizations/$org/reuses/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListOrganizationReuses / Exception =  $e")
            null
        }
        emit(value)
    }

}