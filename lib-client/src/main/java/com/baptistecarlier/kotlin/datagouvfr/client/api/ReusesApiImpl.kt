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
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class ReusesApiImpl(private val client: HttpClient) : ReusesApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListReuses(
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
    ): Flow<DgfrResource<ReusePage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("tag", tag)
        builder.appendIfNotNull("organization", organization)
        builder.appendIfNotNull("owner", owner)
        builder.appendIfNotNull("dataset", dataset)
        builder.appendIfNotNull("type", type)
        builder.appendIfNotNull("datasets", datasets)
        builder.appendIfNotNull("followers", followers)
        builder.appendIfNotNull("badge", badge)
        builder.appendIfNotNull("featured", featured)
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "reuses/?${builder.urlEncore()}"
        )
    }

    override suspend fun postCreateReuse(payload: Reuse): Flow<DgfrResource<Reuse>> = loadingFlow {
        client.post(
            path = "reuses/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun getAvailableReuseBadges(): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.get<HttpResponse>(
            path = "reuses/badges/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getSuggestReuses(q: String, size: Int?): Flow<DgfrResource<List<ReuseSuggestion>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)
        builder.appendIfNotNull("size", size)

        client.get(
            path = "reuses/suggest/?${builder.urlEncore()}"
        )
    }

    override suspend fun getReuseTypes(): Flow<DgfrResource<List<ReuseType>>> = loadingFlow {
        client.get(
            path = "reuses/types/"
        )
    }

    override suspend fun deleteUnfollowReuse(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "reuses/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getListReuseFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<DgfrResource<FollowPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "reuses/$id/followers/?${builder.urlEncore()}"
        )
    }

    override suspend fun postFollowReuse(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.post<HttpResponse>(
            path = "reuses/$id/followers/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun deleteReuse(reuse: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "reuses/$reuse/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getReuse(reuse: String): Flow<DgfrResource<Reuse>> = loadingFlow {
        client.get(
            path = "reuses/$reuse/"
        )
    }

    override suspend fun putUpdateReuse(reuse: String, payload: Reuse): Flow<DgfrResource<Reuse>> = loadingFlow {
        client.put(
            path = "reuses/$reuse/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun postAddReuseBadge(reuse: String, payload: Badge): Flow<DgfrResource<Badge>> = loadingFlow {
        client.post(
            path = "reuses/$reuse/badges/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteReuseBadge(badgeKind: String, reuse: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "reuses/$reuse/badges/$badgeKind/"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun postReuseAddDataset(
        reuse: String,
        payload: DatasetReference
    ): Flow<DgfrResource<Reuse>> = loadingFlow {
        client.post(
            path = "reuses/$reuse/datasets/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun deleteUnfeatureReuse(reuse: String): Flow<DgfrResource<Reuse>> = loadingFlow {
        client.delete(
            path = "reuses/$reuse/featured/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun postFeatureReuse(reuse: String): Flow<DgfrResource<Reuse>> = loadingFlow {
        client.post(
            path = "reuses/$reuse/featured/"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun postReuseImage(
        reuse: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "reuses/$reuse/image",
            formData = formData {
                append("file", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    append(HttpHeaders.ContentType, contentType)
                })
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

}