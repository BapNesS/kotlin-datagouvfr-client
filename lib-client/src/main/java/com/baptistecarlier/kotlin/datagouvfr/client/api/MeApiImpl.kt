package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class MeApiImpl(private val client: HttpClient) : MeApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun deleteMe() = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "me/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getMe(): Flow<DgfrResource<Me>> = loadingFlow {
        client.get(
            path = "me/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun putUpdateMe(payload: Me): Flow<DgfrResource<Me>> = loadingFlow {
        client.put(
            path = "me/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteClearApikey(): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "me/apikey/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun postGenerateApikey(): Flow<DgfrResource<ApiKey>> = loadingFlow {
        client.post(
            path = "me/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
        }
    }

    override suspend fun getMyDatasets(): Flow<DgfrResource<List<Dataset>>> = loadingFlow {
        client.get(
            path = "me/datasets/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyMetrics(): Flow<DgfrResource<List<MyMetrics>>> = loadingFlow {
        client.get(
            path = "me/metrics/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyOrgCommunityResources(q: String?): Flow<DgfrResource<List<CommunityResource>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)

        client.get(
            path = "me/org_community_resources/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyOrgDatasets(q: String?): Flow<DgfrResource<List<Dataset>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)

        client.get(
            path = "me/org_datasets/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyOrgDiscussions(q: String?): Flow<DgfrResource<List<Discussion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)

        client.get(
            path = "me/org_discussions/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyOrgIssues(q: String?): Flow<DgfrResource<List<Issue>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)

        client.get(
            path = "me/org_issues/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyOrgReuses(q: String?): Flow<DgfrResource<List<Reuse>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)

        client.get(
            path = "me/org_reuses/?${builder.urlEncore()}"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getMyReuses(): Flow<DgfrResource<List<Reuse>>> = loadingFlow {
        client.get(
            path = "me/reuses/"
        ) {
            addApiKey(apiKey)
        }
    }

}
