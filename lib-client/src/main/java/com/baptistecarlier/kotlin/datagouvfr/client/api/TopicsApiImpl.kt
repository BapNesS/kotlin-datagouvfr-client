package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Topic
import com.baptistecarlier.kotlin.datagouvfr.client.model.TopicPage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class TopicsApiImpl(private val client: HttpClient) : TopicsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListTopics(page: Int?, pageSize: Int?): Flow<DgfrCallState<TopicPage>> = loadingFlow {
        client.get(
            path = "topics/"
        ) {
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postCreateTopic(payload: Topic): Flow<DgfrCallState<Topic>> = loadingFlow {
        client.post(
            path = "topics/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteTopic(topic: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "topics/$topic/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getTopic(topic: String): Flow<DgfrCallState<Topic>> = loadingFlow {
        client.get(
            path = "topics/$topic/"
        )
    }

    override fun putUpdateTopic(topic: String, payload: Topic): Flow<DgfrCallState<Topic>> = loadingFlow {
        client.put(
            path = "topics/$topic/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }
}
