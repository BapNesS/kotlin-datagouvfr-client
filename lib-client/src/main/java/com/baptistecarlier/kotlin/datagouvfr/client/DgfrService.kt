package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.*
import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.cio.*
import io.ktor.client.features.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.features.logging.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*

private val httpClient: HttpClient by lazy {
    HttpClient(CIO) {
        val timeOut: Long = 60000
        val host = "www.data.gouv.fr"
        val basePath = "/api/1/"

        expectSuccess = true

        this.defaultRequest {
            url.host = host
            url.protocol = URLProtocol.HTTPS
            url.encodedPath = basePath + url.encodedPath
        }
        installers(timeOut)
        validators()
    }
}

fun HttpClientConfig<*>.validators() {
    HttpResponseValidator {
        handleResponseException { exception ->
            if (exception !is ClientRequestException) {
                return@handleResponseException
            }

            val status = exception.response.status.value
            throw DgfrException(status)
        }
    }
}

private fun HttpClientConfig<CIOEngineConfig>.installers(
    timeOut: Long
) {
    install(JsonFeature) {
        serializer = KotlinxSerializer(
            kotlinx.serialization.json.Json {
                prettyPrint = true
                isLenient = true
                ignoreUnknownKeys = true
            }
        )

        engine {
            requestTimeout = timeOut
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
private val reusesApi by lazy { ReusesApiImpl(httpClient) }
private val organizationsApi by lazy { OrganizationsApiImpl(httpClient) }
private val workersApi by lazy { WorkersApiImpl(httpClient) }
private val tagsApi by lazy { TagsApiImpl(httpClient) }
private val topicsApi by lazy { TopicsApiImpl(httpClient) }
private val postsApi by lazy { PostsApiImpl(httpClient) }
private val transferApi by lazy { TransferApiImpl(httpClient) }
private val notificationsApi by lazy { NotificationsApiImpl(httpClient) }
private val avatarsApi by lazy { AvatarsApiImpl(httpClient) }
private val harvestApiImpl by lazy { HarvestApiImpl(httpClient) }

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
    ReusesApi by reusesApi,
    OrganizationsApi by organizationsApi,
    WorkersApi by workersApi,
    TagsApi by tagsApi,
    TopicsApi by topicsApi,
    PostsApi by postsApi,
    TransferApi by transferApi,
    NotificationsApi by notificationsApi,
    AvatarsApi by avatarsApi,
    HarvestApi by harvestApiImpl {

    init {
        setApiKey(apiKey)
    }

    override fun setApiKey(apiKey: String) {
        meApi.setApiKey(apiKey)
        siteApi.setApiKey(apiKey)
        issuesApi.setApiKey(apiKey)
        discussionsApiImpl.setApiKey(apiKey)
        usersApi.setApiKey(apiKey)
        meApi.setApiKey(apiKey)
        datasetsApi.setApiKey(apiKey)
        reusesApi.setApiKey(apiKey)
        organizationsApi.setApiKey(apiKey)
        workersApi.setApiKey(apiKey)
        topicsApi.setApiKey(apiKey)
        postsApi.setApiKey(apiKey)
        transferApi.setApiKey(apiKey)
        notificationsApi.setApiKey(apiKey)
        harvestApiImpl.setApiKey(apiKey)
    }
}
