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
        val timeOut: Long = 60000
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

private val siteApi by lazy { SiteApiImpl(httpClient) }
private val spatialApi by lazy { SpatialApiImpl(httpClient) }
private val issuesApi by lazy { IssuesApiImpl(httpClient) }
private val discussionsApiImpl by lazy { DiscussionsApiImpl(httpClient) }
private val usersApi by lazy { UsersApiImpl(httpClient) }
private val meApi by lazy { MeApiImpl(httpClient) }
private val datasetsApi by lazy { DatasetsApiImpl(httpClient) }
private val workersApi by lazy { WorkersApiImpl(httpClient) }
private val tagsApi by lazy { TagsApiImpl(httpClient) }
private val topicsApi by lazy { TopicsApiImpl(httpClient) }
private val postsApi by lazy { PostsApiImpl(httpClient) }
private val transferApi by lazy { TransferApiImpl(httpClient) }
private val notificationsApi by lazy { NotificationsApiImpl(httpClient) }
private val avatarsApi by lazy { AvatarsApiImpl(httpClient) }

/**
 * @param apiKey Clé d'API (optionnel)
 */
class DgfrService(apiKey: String = "") :
    SiteApi by siteApi,
    SpatialApi by spatialApi,
    IssuesApi by issuesApi,
    DiscussionsApi by discussionsApiImpl,
    UsersApi by usersApi,
    MeApi by meApi,
    DatasetsApi by datasetsApi,
    ReusesApi,
    OrganizationsApi,
    WorkersApi by workersApi,
    TagsApi by tagsApi,
    TopicsApi by topicsApi,
    PostsApi by postsApi,
    TransferApi by transferApi,
    NotificationsApi by notificationsApi,
    AvatarsApi by avatarsApi,
    HarvestApi
{

    init {
        setApiKey(apiKey)
    }

    override fun setApiKey(apiKey: String) {
        meApi.setApiKey(apiKey)
    }

}