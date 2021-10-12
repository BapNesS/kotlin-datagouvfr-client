package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSucces
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class UsersApiImpl(private val client: HttpClient): UsersApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListUsers(
        q: String?,
        facets: List<String>?,
        organization: String?,
        datasets: String?,
        followers: String?,
        sort: String?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<UserPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("facets", facets)
        builder.appendIfNotNull("organization", organization)
        builder.appendIfNotNull("datasets", datasets)
        builder.appendIfNotNull("followers", followers)
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "users/?${builder.urlEncore()}"
        )
    }

    override suspend fun postCreateUser(payload: User): Flow<DgfrResource<User>> = loadingFlow {
        client.post(
            path = "users/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun getUserRoles(): Flow<DgfrResource<List<UserRole>>> = loadingFlow {
        client.get(
            path = "users/roles/"
        )
    }

    override suspend fun getSuggestUsers(q: String, size: Int?): Flow<DgfrResource<List<UserSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "users/suggest/?${builder.urlEncore()}"
        )
    }

    override suspend fun deleteUnfollowUser(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "users/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getListUserFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<FollowPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("id", id)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "users/$id/followers/?${builder.urlEncore()}"
        )
    }

    override suspend fun postFollowUser(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "users/$id/followers/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun deleteUser(user: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "users/$user/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getUser(user: String): Flow<DgfrResource<User>> = loadingFlow {
        client.get(
            path = "users/$user/"
        )
    }

    override suspend fun putUpdateUser(user: String, payload: User): Flow<DgfrResource<User>> = loadingFlow {
        client.put(
            path = "users/$user/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun postUserAvatar(
        user: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "users/$user/avatar",
            formData = formData {
                append("file", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    append(HttpHeaders.ContentType, contentType)
                })
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

}