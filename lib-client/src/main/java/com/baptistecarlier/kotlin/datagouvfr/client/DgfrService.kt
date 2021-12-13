package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.*
import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrException
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.engine.android.*
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

/**
 * @param apiKey Clé d'API (optionnel)
 */
class DgfrService private constructor(
    private val siteApi: SiteApi,
    private val spatialApi: SpatialApi,
    private val issuesApi: IssuesApi,
    private val discussionsApi: DiscussionsApi,
    private val usersApi: UsersApi,
    private val meApi: MeApi,
    private val datasetsApi: DatasetsApi,
    private val reusesApi: ReusesApi,
    private val organizationsApi: OrganizationsApi,
    private val workersApi: WorkersApi,
    private val tagsApi: TagsApi,
    private val topicsApi: TopicsApi,
    private val postsApi: PostsApi,
    private val transferApi: TransferApi,
    private val notificationsApi: NotificationsApi,
    private val avatarsApi: AvatarsApi,
    private val harvestApiImpl: HarvestApi
) :
    SiteApi by siteApi,
    SpatialApi by spatialApi,
    IssuesApi by issuesApi,
    DiscussionsApi by discussionsApi,
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

    override fun setApiKey(apiKey: String) {
        meApi.setApiKey(apiKey)
        siteApi.setApiKey(apiKey)
        issuesApi.setApiKey(apiKey)
        discussionsApi.setApiKey(apiKey)
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

    companion object {
        operator fun invoke(apiKey: String? = null): DgfrService {
            val siteApi = SiteApiImpl(httpClient)
            val spatialApi = SpatialApiImpl(httpClient)
            val issuesApi = IssuesApiImpl(httpClient)
            val discussionsApi = DiscussionsApiImpl(httpClient)
            val usersApi = UsersApiImpl(httpClient)
            val meApi = MeApiImpl(httpClient)
            val datasetsApi = DatasetsApiImpl(httpClient)
            val reusesApi = ReusesApiImpl(httpClient)
            val organizationsApi = OrganizationsApiImpl(httpClient)
            val workersApi = WorkersApiImpl(httpClient)
            val tagsApi = TagsApiImpl(httpClient)
            val topicsApi = TopicsApiImpl(httpClient)
            val postsApi = PostsApiImpl(httpClient)
            val transferApi = TransferApiImpl(httpClient)
            val notificationsApi = NotificationsApiImpl(httpClient)
            val avatarsApi = AvatarsApiImpl(httpClient)
            val harvestApiImpl = HarvestApiImpl(httpClient)

            return DgfrService(
                siteApi,
                spatialApi,
                issuesApi,
                discussionsApi,
                usersApi,
                meApi,
                datasetsApi,
                reusesApi,
                organizationsApi,
                workersApi,
                tagsApi,
                topicsApi,
                postsApi,
                transferApi,
                notificationsApi,
                avatarsApi,
                harvestApiImpl
            ).apply {
                if (apiKey != null) {
                    setApiKey(apiKey)
                }
            }
        }
    }
}
