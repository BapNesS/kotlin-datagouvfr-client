package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class IssuesApiImpl(private val client: HttpClient) : IssuesApi {

    private val tag = "IssueApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListIssues(
        sort: String?,
        closed: Boolean?,
        forIds: List<String>?,
        page: Int?,
        pageSize: Int?
    ): Flow<IssuePage?> = flow {
        Log.d(tag, "getListIssues / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("sort", sort)
            builder.appendIfNotNull("closed", closed)
            // Not sure about this
            forIds?.forEach { item ->
                builder.appendIfNotNull("for", item)
            }
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("pageSize", pageSize)

            val response = client.get<IssuePage?>(
                path = "issues/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListIssues / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateIssue(payload: Issue): Flow<Issue?> = flow {
        Log.d(tag, "postCreateIssue / begin")
        val value = try {
            val response = client.post<Issue?>(
                path = "issues/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateIssue / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getIssue(id: String): Flow<Issue?> = flow {
        Log.d(tag, "getIssue / begin")
        val value = try {
            val response = client.get<Issue?>(
                path = "issues/$id/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getIssue / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCommentIssue(id: String, payload: IssueResponse): Flow<Issue?> = flow {
        Log.d(tag, "postCommentIssue / begin")
        val value = try {
            val response = client.post<Issue?>(
                path = "issues/$id/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCommentIssue / Exception =  $e")
            null
        }
        emit(value)
    }

}