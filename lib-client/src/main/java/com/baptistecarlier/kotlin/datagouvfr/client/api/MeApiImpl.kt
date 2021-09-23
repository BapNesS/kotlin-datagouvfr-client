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

class MeApiImpl(private val client: HttpClient) : MeApi {

    private val tag = "MeApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun deleteMe(): Flow<Boolean?> = flow {
        Log.d(tag, "deleteMe / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "me/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteMe / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMe(): Flow<Me?> = flow {
        Log.d(tag, "getMe / begin")
        val value = try {
            val response = client.get<Me>(
                path = "me/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMe / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateMe(payload: Me): Flow<Me?> = flow {
        Log.d(tag, "putUpdateMe / begin")
        val value = try {
            val response = client.put<Me>(
                path = "me/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateMe / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteClearApikey(): Flow<Boolean?> = flow {
        Log.d(tag, "deleteClearApikey / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "me/apikey/"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in HttpCodeRangeSucces)
        } catch (e: Exception) {
            Log.d(tag, "deleteClearApikey / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postGenerateApikey(): Flow<ApiKey?> = flow {
        Log.d(tag, "postGenerateApikey / begin")
        val value = try {
            val response = client.post<ApiKey>(
                path = "me/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postGenerateApikey / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyDatasets(): Flow<List<Dataset>?> = flow {
        Log.d(tag, "getMyDatasets / begin")
        val value = try {
            val response = client.get<List<Dataset>>(
                path = "me/datasets/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyMetrics(): Flow<List<MyMetrics>?> = flow {
        Log.d(tag, "getMyMetrics / begin")
        val value = try {
            val response = client.get<List<MyMetrics>>(
                path = "me/metrics/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyMetrics / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyOrgCommunityResources(q: String?): Flow<List<CommunityResource>?> = flow {
        Log.d(tag, "getMyOrgCommunityResources / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)

            val response = client.get<List<CommunityResource>>(
                path = "me/org_community_resources/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyOrgCommunityResources / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyOrgDatasets(q: String?): Flow<List<Dataset>?> = flow {
        Log.d(tag, "getMyOrgDatasets / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)

            val response = client.get<List<Dataset>>(
                path = "me/org_datasets/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyOrgDatasets / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyOrgDiscussions(q: String?): Flow<List<Discussion>?> = flow {
        Log.d(tag, "getMyOrgDiscussions / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)

            val response = client.get<List<Discussion>>(
                path = "me/org_discussions/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyOrgDiscussions / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyOrgIssues(q: String?): Flow<List<Issue>?> = flow {
        Log.d(tag, "getMyOrgIssues / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)

            val response = client.get<List<Issue>>(
                path = "me/org_issues/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyOrgIssues / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyOrgReuses(q: String?): Flow<List<Reuse>?> = flow {
        Log.d(tag, "getMyOrgReuses / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)

            val response = client.get<List<Reuse>>(
                path = "me/org_reuses/?${builder.urlEncore()}"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyOrgReuses / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getMyReuses(): Flow<List<Reuse>?> = flow {
        Log.d(tag, "getMyReuses / begin")
        val value = try {
            val response = client.get<List<Reuse>>(
                path = "me/reuses/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getMyReuses / Exception =  $e")
            null
        }
        emit(value)
    }

}
