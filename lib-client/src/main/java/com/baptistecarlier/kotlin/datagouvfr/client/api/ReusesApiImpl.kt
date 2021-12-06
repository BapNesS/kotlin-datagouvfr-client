package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingApiParamter
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

internal class ReusesApiImpl(private val client: HttpClient) : ReusesApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    @MissingApiParamter
    override fun getListReuses(
        q: String?,
        /*facets: List<String>?,*/
        tag: String?,
        organization: String?,
        owner: String?,
        dataset: String?,
        type: String?,
        datasets: String?,
        followers: String?,
        badge: String?,
        featured: Boolean?,
        sort: String?,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<ReusePage>> = loadingFlow {
        client.get(
            path = "reuses/"
        ) {
            parameter("q", q)
            parameter("tag", tag)
            parameter("organization", organization)
            parameter("owner", owner)
            parameter("dataset", dataset)
            parameter("type", type)
            parameter("datasets", datasets)
            parameter("followers", followers)
            parameter("badge", badge)
            parameter("featured", featured)
            parameter("sort", sort)
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postCreateReuse(payload: Reuse): Flow<DgfrCallState<Reuse>> = loadingFlow {
        client.post(
            path = "reuses/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getAvailableReuseBadges(): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "reuses/badges/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getSuggestReuses(q: String, size: Int?): Flow<DgfrCallState<List<ReuseSuggestion>>> = loadingFlow {
        client.get(
            path = "reuses/suggest/"
        ) {
            parameter("q", q)
            parameter("size", size)
        }
    }

    override fun getReuseTypes(): Flow<DgfrCallState<List<ReuseType>>> = loadingFlow {
        client.get(
            path = "reuses/types/"
        )
    }

    override fun deleteUnfollowReuse(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "reuses/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListReuseFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrCallState<FollowPage>> = loadingFlow {
        client.get(
            path = "reuses/$id/followers/"
        ) {
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postFollowReuse(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "reuses/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun deleteReuse(reuse: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "reuses/$reuse/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getReuse(reuse: String): Flow<DgfrCallState<Reuse>> = loadingFlow {
        client.get(
            path = "reuses/$reuse/"
        )
    }

    override fun putUpdateReuse(reuse: String, payload: Reuse): Flow<DgfrCallState<Reuse>> = loadingFlow {
        client.put(
            path = "reuses/$reuse/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun postAddReuseBadge(reuse: String, payload: Badge): Flow<DgfrCallState<Badge>> = loadingFlow {
        client.post(
            path = "reuses/$reuse/badges/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteReuseBadge(badgeKind: String, reuse: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "reuses/$reuse/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun postReuseAddDataset(
        reuse: String,
        payload: DatasetReference
    ): Flow<DgfrCallState<Reuse>> = loadingFlow {
        client.post(
            path = "reuses/$reuse/datasets/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deleteUnfeatureReuse(reuse: String): Flow<DgfrCallState<Reuse>> = loadingFlow {
        client.delete(
            path = "reuses/$reuse/featured/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postFeatureReuse(reuse: String): Flow<DgfrCallState<Reuse>> = loadingFlow {
        client.post(
            path = "reuses/$reuse/featured/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postReuseImage(
        reuse: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "reuses/$reuse/image",
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
}
