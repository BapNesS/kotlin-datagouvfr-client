package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
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
import kotlinx.coroutines.flow.flow

class TopicsApiImpl(private val client: HttpClient) : TopicsApi {

    private val tag = "TopicsApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListTopics(page: Int?, pageSize: Int?): Flow<TopicPage?> = flow {
        Log.d(tag, "getListTopics / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<TopicPage?>(
                path = "topics/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListTopics / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateTopic(payload: Topic): Flow<Topic?> = flow {
        Log.d(tag, "postCreateTopic / begin")
        val value = try {
            val response = client.post<Topic?>(
                path = "topics/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateTopic / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteTopic(topic: String): Flow<Boolean?> = flow {
        Log.d(tag, "postCreateTopic / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "topics/$topic"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "postCreateTopic / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getTopic(topic: String): Flow<Topic?> = flow {
        Log.d(tag, "getTopic / begin")
        val value = try {
            val response = client.get<Topic?>(
                path = "topics/$topic/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getTopic / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateTopic(topic: String, payload: Topic): Flow<Topic?> = flow {
        Log.d(tag, "putUpdateTopic / begin")
        val value = try {
            val response = client.put<Topic?>(
                path = "topics/$topic/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateTopic / Exception =  $e")
            null
        }
        emit(value)
    }

}