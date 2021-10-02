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

class ReusesApiImpl(private val client: HttpClient) : ReusesApi {

    private val tag = "ReusesApiImpl"

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
    ): Flow<ReusePage?> = flow {
        Log.d(this@ReusesApiImpl.tag, "getListReuses / begin")
        val value = try {
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

            val response = client.get<ReusePage>(
                path = "reuses/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(this@ReusesApiImpl.tag, "getListReuses / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateReuse(payload: Reuse): Flow<Reuse?> = flow {
        Log.d(tag, "postCreateReuse / begin")
        val value = try {
            val response = client.post<Reuse>(
                path = "reuses/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getAvailableReuseBadges(): Flow<Boolean?> = flow {
        Log.d(tag, "getAvailableReuseBadges / begin")
        val value = try {
            val response = client.get<HttpResponse>(
                path = "reuses/badges/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "getAvailableReuseBadges / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getSuggestReuses(q: String, size: Int?): Flow<List<ReuseSuggestion>?> = flow {
        Log.d(tag, "getSuggestReuses / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            builder.appendIfNotNull("size", size)

            val response = client.get<List<ReuseSuggestion>>(
                path = "reuses/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggestReuses / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getReuseTypes(): Flow<List<ReuseType>?> = flow {
        Log.d(tag, "getReuseTypes / begin")
        val value = try {
            val response = client.get<List<ReuseType>>(
                path = "reuses/types/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getReuseTypes / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnfollowReuse(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteUnfollowReuse / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "reuses/$id/followers/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteUnfollowReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListReuseFollowers(
        id: String,
        page: Int?,
        pageSize: Int?
    ): Flow<FollowPage?> = flow {
        Log.d(tag, "getListReuseFollowers / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<FollowPage>(
                path = "reuses/$id/followers/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListReuseFollowers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postFollowReuse(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "postFollowReuse / begin")
        val value = try {
            val response = client.post<HttpResponse>(
                path = "reuses/$id/followers/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "postFollowReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteReuse(reuse: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteReuse / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "reuses/$reuse/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getReuse(reuse: String): Flow<Reuse?> = flow {
        Log.d(tag, "getReuse / begin")
        val value = try {
            val response = client.get<Reuse>(
                path = "reuses/$reuse/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateReuse(reuse: String, payload: Reuse): Flow<Reuse?> = flow {
        Log.d(tag, "putUpdateReuse / begin")
        val value = try {
            val response = client.put<Reuse>(
                path = "reuses/$reuse/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postAddReuseBadge(reuse: String, payload: Badge): Flow<Badge?> = flow {
        Log.d(tag, "postAddReuseBadge / begin")
        val value = try {
            val response = client.post<Badge>(
                path = "reuses/$reuse/badges/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postAddReuseBadge / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteReuseBadge(badgeKind: String, reuse: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteReuseBadge / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "reuses/$reuse/badges/$badgeKind/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteReuseBadge / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postReuseAddDataset(
        reuse: String,
        payload: DatasetReference
    ): Flow<Reuse?> = flow {
        Log.d(tag, "postReuseAddDataset / begin")
        val value = try {
            val response = client.post<Reuse>(
                path = "reuses/$reuse/datasets/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postReuseAddDataset / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnfeatureReuse(reuse: String): Flow<Reuse?> = flow {
        Log.d(tag, "deleteUnfeatureReuse / begin")
        val value = try {
            val response = client.delete<Reuse>(
                path = "reuses/$reuse/featured/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "deleteUnfeatureReuse / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postFeatureReuse(reuse: String): Flow<Reuse?> = flow {
        Log.d(tag, "postFeatureReuse / begin")
        val value = try {
            val response = client.post<Reuse>(
                path = "reuses/$reuse/featured/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postFeatureReuse / Exception =  $e")
            null
        }
        emit(value)
    }

}