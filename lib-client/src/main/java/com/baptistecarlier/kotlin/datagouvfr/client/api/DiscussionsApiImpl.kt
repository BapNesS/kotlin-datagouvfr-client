package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionStart
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSucces
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class DiscussionsApiImpl(private val client: HttpClient) : DiscussionsApi {

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
    ): Flow<DgfrResource<DiscussionPage>> = loadingFlow {
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

    override suspend fun postCreateDiscussion(payload: DiscussionStart): Flow<DgfrResource<Discussion>> = loadingFlow {
        client.post(
            path = "discussions/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteDiscussion(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "discussions/$id"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getDiscussion(id: String): Flow<DgfrResource<Discussion>> = loadingFlow {
        client.get(
            path = "discussions/$id/"
        )
    }

    override suspend fun postCommentDiscussion(
        id: String,
        payload: DiscussionResponse
    ): Flow<DgfrResource<Discussion>> = loadingFlow {
        client.post(
            path = "discussions/$id/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteDiscussionComment(id: String, cidx: Int): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "discussions/$id/comments/$cidx"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

}