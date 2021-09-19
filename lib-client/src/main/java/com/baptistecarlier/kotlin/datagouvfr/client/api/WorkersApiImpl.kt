package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.Job
import com.baptistecarlier.kotlin.datagouvfr.client.model.Task
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WorkersApiImpl(private val client: HttpClient) : WorkersApi {

    private val tag = "WorkersApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListJobs(): Flow<List<Job>?> = flow {
        Log.d(tag, "getListJobs / begin")
        val value = try {
            val response = client.get<List<Job>>(
                path = "workers/jobs/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListJobs / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postJobsApi(payload: Job): Flow<Job?> = flow {
        Log.d(tag, "postJobsApi / begin")
        val value = try {
            val response = client.post<Job?>(
                path = "workers/jobs/"
            ) {
                addApiKey(apiKey)
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postJobsApi / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getJobsReferenceApi(): Flow<List<String>?> = flow {
        Log.d(tag, "getJobsReferenceApi / begin")
        val value = try {
            val response = client.get<List<String>>(
                path = "workers/jobs/schedulables/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getJobsReferenceApi / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun deleteJobApi(id: String): Flow<Boolean?> = flow {
        Log.d(tag, "deleteJobApi / begin")
        val value = try {
            val response = client.delete<HttpResponse>(
                path = "workers/jobs/$id"
            ) {
                addApiKey(apiKey)
            }
            (response.status.value in 200..299)
        } catch (e: Exception) {
            Log.d(tag, "deleteJobApi / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getJobApi(id: String): Flow<Job?> = flow {
        Log.d(tag, "getJobApi / begin")
        val value = try {
            val response = client.get<Job?>(
                path = "workers/jobs/$id"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getJobApi / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun putJobApi(id: String): Flow<Job?> = flow {
        Log.d(tag, "putJobApi / begin")
        val value = try {
            val response = client.put<Job?>(
                path = "workers/jobs/$id"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "putJobApi / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getTaskApi(id: String): Flow<Task?> = flow {
        Log.d(tag, "getTaskApi / begin")
        val value = try {
            val response = client.get<Task?>(
                path = "workers/tasks/$id"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getTaskApi / Exception =  $e")
            null
        }
        emit(value)
    }

}