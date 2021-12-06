package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class UsersApiImpl(private val client: HttpClient) : UsersApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    override fun getListUsers(
        q: String?,
        /*facets: List<String>?,*/
        organization: String?,
        datasets: String?,
        followers: String?,
        sort: String?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<UserPage>> = loadingFlow {
        client.get(
            path = "users/"
        ) {
            parameter("q", q)
            /*parameter("facets", facets)*/
            parameter("organization", organization)
            parameter("datasets", datasets)
            parameter("followers", followers)
            parameter("sort", sort)
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateUser(payload: User): Flow<DgfrCallState<User>> = loadingFlow {
        client.post(
            path = "users/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getUserRoles(): Flow<DgfrCallState<List<UserRole>>> = loadingFlow {
        client.get(
            path = "users/roles/"
        )
    }

    override fun getSuggestUsers(q: String, size: Int?): Flow<DgfrCallState<List<UserSuggestion>>> = loadingFlow {
        client.get(
            path = "users/suggest/"
        ) {
            parameter("q", q)
            parameter("size", size)
        }
    }

    override fun deleteUnfollowUser(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "users/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListUserFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<FollowPage>> = loadingFlow {
        client.get(
            path = "users/$id/followers/"
        ) {
            parameter("id", id)
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postFollowUser(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "users/$id/followers/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun deleteUser(user: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "users/$user/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getUser(user: String): Flow<DgfrCallState<User>> = loadingFlow {
        client.get(
            path = "users/$user/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun putUpdateUser(user: String, payload: User): Flow<DgfrCallState<User>> = loadingFlow {
        client.put(
            path = "users/$user/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun postUserAvatar(
        user: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "users/$user/avatar",
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
}
