package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSucces
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class TopicsApiImpl(private val client: HttpClient) : TopicsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListTopics(page: Int?, pageSize: Int?): Flow<DgfrResource<TopicPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "topics/?${builder.urlEncore()}"
        )
    }

    override suspend fun postCreateTopic(payload: Topic): Flow<DgfrResource<Topic>> = loadingFlow {
        client.post(
            path = "topics/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteTopic(topic: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "topics/$topic"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getTopic(topic: String): Flow<DgfrResource<Topic>> = loadingFlow {
        client.get(
            path = "topics/$topic/"
        )
    }

    override suspend fun putUpdateTopic(topic: String, payload: Topic): Flow<DgfrResource<Topic>> = loadingFlow {
        client.put(
            path = "topics/$topic/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

}