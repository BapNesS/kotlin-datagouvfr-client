package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class UsersApiImpl(private val client: HttpClient): UsersApi {

    private val tag = "UsersApiImpl"

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
    ): Flow<UserPage?> = flow {
        Log.d(tag, "getListUsers / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("facets", facets)
            builder.appendIfNotNull("organization", organization)
            builder.appendIfNotNull("datasets", datasets)
            builder.appendIfNotNull("followers", followers)
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<UserPage>(
                path = "users/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListUsers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateUser(payload: User): Flow<User?> = flow {
        Log.d(tag, "postCreateUser / begin")
        val value = try {
            val response = client.post<User?>(
                path = "users/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateUser / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getUserRoles(): Flow<List<UserRole>?> = flow {
        Log.d(tag, "getUserRoles / begin")
        val value = try {
            val response = client.get<List<UserRole>?>(
                path = "users/roles/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getUserRoles / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestUsers(q: String, size: Int?): Flow<List<UserSuggestion>?> = flow {
        Log.d(tag, "getSuggestUsers / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<UserSuggestion>>(
                path = "users/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestUsers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnfollowUser(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteUnfollowUser / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "users/$id/followers/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in 200..299)
        } catch (e: Exception) {
            Log.d(tag, "deleteUnfollowUser / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListUserFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<FollowPage?> = flow {
        Log.d(tag, "getListUserFollowers / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("id", id)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<FollowPage>(
                path = "users/$id/followers/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListUserFollowers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postFollowUser(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "postFollowUser / begin")
        val value = try {
            val response = client.post<HttpResponse>(
                path = "users/$id/followers/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
            }
            (response.status.value in 200..299)
        } catch (e: Exception) {
            Log.d(tag, "postFollowUser / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUser(user: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteUser / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "users/$user/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in 200..299)
        } catch (e: Exception) {
            Log.d(tag, "deleteUser / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getUser(user: String): Flow<User?> = flow {
        Log.d(tag, "getUser / begin")
        val value = try {
            val response = client.get<User>(
                path = "users/$user/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getUser / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateUser(user: String, payload: User): Flow<User?> = flow {
        Log.d(tag, "putUpdateUser / begin")
        val value = try {
            val response = client.put<User?>(
                path = "users/$user/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateUser / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postUserAvatar(user: String, bbox: String?): Flow<UploadedImage?> {
        TODO("Not yet implemented")
    }

}