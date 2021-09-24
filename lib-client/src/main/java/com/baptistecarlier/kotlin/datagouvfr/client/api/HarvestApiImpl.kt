package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class HarvestApiImpl(private val client: HttpClient): HarvestApi {

    private val tag = "HarvestApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getHarvestBackends(): Flow<HarvestBackend?> = flow {
        Log.d(tag, "getHarvestBackends / begin")
        val value = try {
            val response = client.get<HarvestBackend>(
                path = "harvest/backends/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getHarvestBackends / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getHarvestJob(
        ident: String,
        page: Int?,
        pageSize: Int?
    ): Flow<HarvestJobPage?> = flow {
        Log.d(tag, "getHarvestJob / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)

            val response = client.get<HarvestJobPage>(
                path = "harvest/job/$ident/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getHarvestJob / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListHarvesterApi(): Flow<List<String>?> = flow {
        Log.d(tag, "getListHarvesterApi / begin")
        val value = try {
            val response = client.get<List<String>>(
                path = "harvest/job_status/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListHarvesterApi / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postPreviewHarvestSourceConfig(payload: HarvestSource): Flow<HarvestJobPreview?> = flow {
        Log.d(tag, "postPreviewHarvestSourceConfig / begin")
        val value = try {
            val response = client.post<HarvestJobPreview>(
                path = "harvest/sources/preview/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postPreviewHarvestSourceConfig / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteHarvestSource(ident: String): Flow<HarvestSource?> = flow {
        Log.d(tag, "deleteHarvestSource / begin")
        val value = try {
            val response = client.delete<HarvestSource>(
                path = "harvest/source/$ident/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "deleteHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getHarvestSource(ident: String): Flow<HarvestSource?> = flow {
        Log.d(tag, "getHarvestSource / begin")
        val value = try {
            val response = client.get<HarvestSource>(
                path = "harvest/source/$ident/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putUpdateHarvestSource(
        ident: String,
        payload: HarvestSource
    ): Flow<HarvestSource?> = flow {
        Log.d(tag, "putUpdateHarvestSource / begin")
        val value = try {
            val response = client.put<HarvestSource>(
                path = "harvest/source/$ident/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putUpdateHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListHarvestJobs(
        ident: String,
        page: Int?,
        pageSize: Int?
    ): Flow<HarvestJob?> = flow {
        Log.d(tag, "getListHarvestJobs / begin")
        val value = try {
            val response = client.get<HarvestJob>(
                path = "harvest/source/$ident/jobs/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListHarvestJobs / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getPreviewHarvestSource(ident: String): Flow<HarvestJobPreview?> = flow {
        Log.d(tag, "getPreviewHarvestSource / begin")
        val value = try {
            val response = client.get<HarvestJobPreview>(
                path = "harvest/source/$ident/preview/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getPreviewHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteUnscheduleHarvestSource(ident: String): Flow<HarvestSource?> = flow {
        Log.d(tag, "deleteUnscheduleHarvestSource / begin")
        val value = try {
            val response = client.delete<HarvestSource>(
                path = "harvest/source/$ident/schedule"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "deleteUnscheduleHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postScheduleHarvestSource(
        ident: String,
        payload: String
    ): Flow<HarvestSource?> = flow {
        Log.d(tag, "postScheduleHarvestSource / begin")
        val value = try {
            val response = client.post<HarvestSource>(
                path = "harvest/source/$ident/schedule/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postScheduleHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postValidateHarvestSource(
        ident: String,
        payload: HarvestSourceValidation
    ): Flow<HarvestSource?> = flow {
        Log.d(tag, "postValidateHarvestSource / begin")
        val value = try {
            val response = client.post<HarvestSource>(
                path = "harvest/source/$ident/validate/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postValidateHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getListHarvestSources(
        page: Int?,
        pageSize: Int?,
        owner: String?,
        deleted: Boolean?
    ): Flow<List<HarvestSourcePage>?> = flow {
        Log.d(tag, "getListHarvestSources / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("page", page)
            builder.appendIfNotNull("page_size", pageSize)
            builder.appendIfNotNull("owner", page)
            builder.appendIfNotNull("deleted", page)

            val response = client.get<List<HarvestSourcePage>>(
                path = "harvest/sources/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListHarvestSources / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postCreateHarvestSource(payload: HarvestSource): Flow<HarvestSource?> = flow {
        Log.d(tag, "postCreateHarvestSource / begin")
        val value = try {
            val response = client.post<HarvestSource>(
                path = "harvest/sources/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postCreateHarvestSource / Exception =  $e")
            null
        }
        emit(value)
    }

}