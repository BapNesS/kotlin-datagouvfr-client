package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionStart
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class DiscussionsApiImpl(private val client: HttpClient) : DiscussionsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListDiscussions(
        sort: String?,
        closed: Boolean?,
        forIds: List<String>?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<DiscussionPage>> = loadingFlow {
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
            path = "discussions/?${builder.urlEncore()}"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCreateDiscussion(payload: DiscussionStart): Flow<DgfrCallState<Discussion>> = loadingFlow {
        client.post(
            path = "discussions/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteDiscussion(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "discussions/$id"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getDiscussion(id: String): Flow<DgfrCallState<Discussion>> = loadingFlow {
        client.get(
            path = "discussions/$id/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postCommentDiscussion(
        id: String,
        payload: DiscussionResponse
    ): Flow<DgfrCallState<Discussion>> = loadingFlow {
        client.post(
            path = "discussions/$id/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteDiscussionComment(id: String, cidx: Int): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "discussions/$id/comments/$cidx"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }
}
