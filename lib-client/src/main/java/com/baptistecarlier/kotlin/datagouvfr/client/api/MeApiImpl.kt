package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class MeApiImpl(private val client: HttpClient) : MeApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun deleteMe() = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "me/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getMe(): Flow<DgfrCallState<Me>> = loadingFlow {
        client.get(
            path = "me/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun putUpdateMe(payload: Me): Flow<DgfrCallState<Me>> = loadingFlow {
        client.put(
            path = "me/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteClearApikey(): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "me/apikey"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun postGenerateApikey(): Flow<DgfrCallState<ApiKey>> = loadingFlow {
        client.post(
            path = "me/apikey"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
        }
    }

    override fun postMyAvatar(
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "me/avatar",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

    override fun getMyDatasets(): Flow<DgfrCallState<List<Dataset>>> = loadingFlow {
        client.get(
            path = "me/datasets/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun getMyMetrics(): Flow<DgfrCallState<List<MyMetrics>>> = loadingFlow {
        client.get(
            path = "me/metrics/"
        ) {
            addApiKey(apiKey)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getMyOrgCommunityResources(q: String?): Flow<DgfrCallState<List<CommunityResource>>> = loadingFlow {
        client.get(
            path = "me/org_community_resources/"
        ) {
            addApiKey(apiKey)
            parameter("q", q)
        }
    }

    override fun getMyOrgDatasets(q: String?): Flow<DgfrCallState<List<Dataset>>> = loadingFlow {
        client.get(
            path = "me/org_datasets/"
        ) {
            addApiKey(apiKey)
            parameter("q", q)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getMyOrgDiscussions(q: String?): Flow<DgfrCallState<List<Discussion>>> = loadingFlow {
        client.get(
            path = "me/org_discussions/"
        ) {
            addApiKey(apiKey)
            parameter("q", q)
        }
    }

    @OptIn(MissingFieldMapping::class)
    override fun getMyOrgIssues(q: String?): Flow<DgfrCallState<List<Issue>>> = loadingFlow {
        client.get(
            path = "me/org_issues/"
        ) {
            addApiKey(apiKey)
            parameter("q", q)
        }
    }

    override fun getMyOrgReuses(q: String?): Flow<DgfrCallState<List<Reuse>>> = loadingFlow {
        client.get(
            path = "me/org_reuses/"
        ) {
            addApiKey(apiKey)
            parameter("q", q)
        }
    }

    override fun getMyReuses(): Flow<DgfrCallState<List<Reuse>>> = loadingFlow {
        client.get(
            path = "me/reuses/"
        ) {
            addApiKey(apiKey)
        }
    }
}
