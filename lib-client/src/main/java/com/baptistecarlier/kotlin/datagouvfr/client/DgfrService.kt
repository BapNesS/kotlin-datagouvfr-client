package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.*
import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrNetworkException
import com.baptistecarlier.kotlin.datagouvfr.client.logger.DgfrHttpLogger
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.statement.*
import io.ktor.http.*


private val httpClient: HttpClient by lazy {
    HttpClient(CIO) {
        val timeOut: Long = 60_000
        val host = "www.data.gouv.fr"
        val basePath = "/api/1/"

        this.defaultRequest {
            url.host = host
            url.protocol = URLProtocol.HTTPS
            url.encodedPath = basePath + url.encodedPath
        }
        install(JsonFeature) {
            serializer = KotlinxSerializer(kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            })

            engine {
                requestTimeout = timeOut
            }
        }

        install(Logging) {
            logger = DgfrHttpLogger()
            level = LogLevel.ALL
        }

        HttpResponseValidator {
            handleResponseException { exception ->
                if (exception !is ClientRequestException) return@handleResponseException

                val exceptionResponse = exception.response
                val exceptionResponseStatus = exception.response.status
                throw DgfrNetworkException(exceptionResponse, exceptionResponseStatus)
            }
        }
    }
}

private val datasetsApi by lazy { DatasetsApiImpl(httpClient) }
private val meApi by lazy { MeApiImpl(httpClient) }
private val tagsApi by lazy { TagsApiImpl(httpClient) }
private val avatarsApi by lazy { AvatarsApiImpl(httpClient) }
private val notificationsApi by lazy { NotificationsApiImpl(httpClient) }

class DgfrService(apiKey: String = "") :
    DatasetsApi by datasetsApi,
    MeApi by meApi,
    TagsApi by tagsApi,
    AvatarsApi by avatarsApi,
    NotificationsApi by notificationsApi
{

    init {
        setApiKey(apiKey)
    }

    override fun setApiKey(apiKey: String) {
        meApi.setApiKey(apiKey)
    }

}
