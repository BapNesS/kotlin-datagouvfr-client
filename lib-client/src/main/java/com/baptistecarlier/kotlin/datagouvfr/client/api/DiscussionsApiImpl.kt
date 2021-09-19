package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionStart
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class DiscussionsApiImpl(private val client: HttpClient) : DiscussionsApi {

    private val tag = "DiscussionsApi"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListDiscussions(
        sort: String?,
        closed: Boolean?,
        forIds: List<String>?,
        page: Int?,
        pageSize: Int?
    ): Flow<DiscussionPage?> = flow {
        Log.d(tag, "getListDiscussions / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("closed", closed)
            // Not sure about this
            forIds?.forEach { item ->
                builder.appendIfNotNull("for", item)
            }
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<DiscussionPage?>(
                path = "discussions/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListDiscussions / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateDiscussion(payload: DiscussionStart): Flow<Discussion?> = flow {
        Log.d(tag, "postCreateDiscussion / begin")
        val value = try {
            val response = client.post<Discussion?>(
                path = "discussions/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateDiscussion / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteDiscussion(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteDiscussion / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "discussions/$id"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in 200..299)
        } catch (e: Exception) {
            Log.d(tag, "deleteDiscussion / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getDiscussion(id: String): Flow<Discussion?> = flow {
        Log.d(tag, "getDiscussion / begin")
        val value = try {
            val response = client.get<Discussion?>(
                path = "discussions/$id/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getDiscussion / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCommentDiscussion(
        id: String,
        payload: DiscussionResponse
    ): Flow<Discussion?> = flow {
        Log.d(tag, "postCommentDiscussion / begin")
        val value = try {
            val response = client.post<Discussion?>(
                path = "discussions/$id/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCommentDiscussion / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteDiscussionComment(id: String, cidx: Int): Flow<Boolean?> = flow {
        Log.d(tag, "deleteDiscussionComment / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "discussions/$id/comments/$cidx"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in 200..299)
        } catch (e: Exception) {
            Log.d(tag, "deleteDiscussionComment / Exception =  $e")
            null
        }
        emit(value)
    }

}