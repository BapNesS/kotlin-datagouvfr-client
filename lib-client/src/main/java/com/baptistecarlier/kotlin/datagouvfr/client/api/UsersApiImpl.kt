package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
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
    ): Flow<DgfrResource<UserPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        /*builder.appendIfNotNull("facets", facets)*/
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

    override fun postCreateUser(payload: User): Flow<DgfrResource<User>> = loadingFlow {
        client.post(
            path = "users/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getUserRoles(): Flow<DgfrResource<List<UserRole>>> = loadingFlow {
        client.get(
            path = "users/roles/"
        )
    }

    override fun getSuggestUsers(q: String, size: Int?): Flow<DgfrResource<List<UserSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "users/suggest/?${builder.urlEncore()}"
        )
    }

    override fun deleteUnfollowUser(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
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
    ): Flow<DgfrResource<FollowPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("id", id)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "users/$id/followers/?${builder.urlEncore()}"
        )
    }

    override fun postFollowUser(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "users/$id/followers/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun deleteUser(user: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "users/$user/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getUser(user: String): Flow<DgfrResource<User>> = loadingFlow {
        client.get(
            path = "users/$user/"
        )
    }

    override fun putUpdateUser(user: String, payload: User): Flow<DgfrResource<User>> = loadingFlow {
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
    ): Flow<DgfrResource<UploadedImage>> = loadingFlow {
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
