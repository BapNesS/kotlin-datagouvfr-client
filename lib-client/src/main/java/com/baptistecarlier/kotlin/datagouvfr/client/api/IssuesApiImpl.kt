package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Issue
import com.baptistecarlier.kotlin.datagouvfr.client.model.IssuePage
import com.baptistecarlier.kotlin.datagouvfr.client.model.IssueResponse
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class IssuesApiImpl(private val client: HttpClient) : IssuesApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListIssues(
        sort: String?,
        closed: Boolean?,
        forIds: List<String>?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<IssuePage>> = loadingFlow {
        client.get(
            path = "issues/"
        ) {
            parameter("sort", sort)
            parameter("closed", closed)
            // TODO Not sure about this
            forIds?.forEach { item ->
                parameter("for", item)
            }
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateIssue(payload: Issue): Flow<DgfrCallState<Issue>> = loadingFlow {
        client.post(
            path = "issues/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getIssue(id: String): Flow<DgfrCallState<Issue>> = loadingFlow {
        client.get(
            path = "issues/$id/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCommentIssue(id: String, payload: IssueResponse): Flow<DgfrCallState<Issue>> = loadingFlow {
        client.post(
            path = "issues/$id/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }
}
