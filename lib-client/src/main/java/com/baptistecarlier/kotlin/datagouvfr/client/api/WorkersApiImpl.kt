package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Job
import com.baptistecarlier.kotlin.datagouvfr.client.model.Task
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSucces
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class WorkersApiImpl(private val client: HttpClient) : WorkersApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListJobs(): Flow<DgfrResource<List<Job>>> = loadingFlow {
        client.get(
            path = "workers/jobs/"
        )
    }

    override suspend fun postJobsApi(payload: Job): Flow<DgfrResource<Job>> = loadingFlow {
        client.post(
            path = "workers/jobs/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override suspend fun getJobsReferenceApi(): Flow<DgfrResource<List<String>>> = loadingFlow {
        client.get(
            path = "workers/jobs/schedulables/"
        )
    }

    override suspend fun deleteJobApi(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "workers/jobs/$id"
        ) {
            addApiKey(apiKey)
        }
        (response.status.value in HttpCodeRangeSucces)
    }

    override suspend fun getJobApi(id: String): Flow<DgfrResource<Job>> = loadingFlow {
        client.get(
            path = "workers/jobs/$id"
        )
    }

    override suspend fun putJobApi(id: String): Flow<DgfrResource<Job>> = loadingFlow {
        client.put(
            path = "workers/jobs/$id"
        ) {
            addApiKey(apiKey)
        }
    }

    override suspend fun getTaskApi(id: String): Flow<DgfrResource<Task>> = loadingFlow {
        client.get(
            path = "workers/tasks/$id"
        )
    }

}