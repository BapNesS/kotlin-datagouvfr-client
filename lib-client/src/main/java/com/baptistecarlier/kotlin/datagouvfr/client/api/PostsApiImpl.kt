package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.Post
import com.baptistecarlier.kotlin.datagouvfr.client.model.PostPage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSucces
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class PostsApiImpl(private val client: HttpClient) : PostsApi {

    private val tag = "PostsApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListPosts(page: Int?, pageSize: Int?, sort: String?): Flow<PostPage?> = flow {
        Log.d(tag, "getListPosts / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<PostPage>(
                path = "posts/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListPosts / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreatePost(payload: Post): Flow<Post?> = flow {
        Log.d(tag, "postCreatePost / begin")
        val value = try {
            val response = client.post<Post?>(
                path = "posts/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreatePost / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deletePost(post: String): Flow<Boolean?> = flow {
        Log.d(tag, "deletePost / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "posts/$post/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deletePost / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getPost(post: String): Flow<Post?> = flow {
        Log.d(tag, "getPost / begin")
        val value = try {
            val response = client.get<Post>(
                path = "posts/$post/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getPost / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdatePost(post: String, payload: Post): Flow<Post?> = flow {
        Log.d(tag, "putUpdatePost / begin")
        val value = try {
            val response = client.put<Post?>(
                path = "posts/$post/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdatePost / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnpublishPost(post: String): Flow<Post?> = flow {
        Log.d(tag, "deleteUnpublishPost / begin")
        val value = try {
            val response = client.delete<Post>(
                path = "posts/$post/publish/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "deleteUnpublishPost / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postPublishPost(post: String): Flow<Post?> = flow {
        Log.d(tag, "postPublishPost / begin")
        val value = try {
            val response = client.post<Post?>(
                path = "posts/$post/publish/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postPublishPost / Exception =  $e")
            null
        }
        emit(value)
    }
}