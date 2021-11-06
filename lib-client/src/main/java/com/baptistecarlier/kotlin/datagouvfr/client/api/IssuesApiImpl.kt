package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
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
    ): Flow<DgfrResource<IssuePage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("closed", closed)
        // Not sure about this
        forIds?.forEach { item ->
            builder.appendIfNotNull("for", item)
        }
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "issues/?${builder.urlEncore()}"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateIssue(payload: Issue): Flow<DgfrResource<Issue>> = loadingFlow {
        client.post(
            path = "issues/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getIssue(id: String): Flow<DgfrResource<Issue>> = loadingFlow {
        client.get(
            path = "issues/$id/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCommentIssue(id: String, payload: IssueResponse): Flow<DgfrResource<Issue>> = loadingFlow {
        client.post(
            path = "issues/$id/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }
}
