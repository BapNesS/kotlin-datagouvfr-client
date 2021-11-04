package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Job
import com.baptistecarlier.kotlin.datagouvfr.client.model.Task
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class WorkersApiImpl(private val client: HttpClient) : WorkersApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun getListJobs(): Flow<DgfrResource<List<Job>>> = loadingFlow {
        client.get(
            path = "workers/jobs/"
        )
    }

    override fun postJobsApi(payload: Job): Flow<DgfrResource<Job>> = loadingFlow {
        client.post(
            path = "workers/jobs/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getJobsReferenceApi(): Flow<DgfrResource<List<String>>> = loadingFlow {
        client.get(
            path = "workers/jobs/schedulables/"
        )
    }

    override fun deleteJobApi(id: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "workers/jobs/$id"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getJobApi(id: String): Flow<DgfrResource<Job>> = loadingFlow {
        client.get(
            path = "workers/jobs/$id"
        )
    }

    override fun putJobApi(id: String): Flow<DgfrResource<Job>> = loadingFlow {
        client.put(
            path = "workers/jobs/$id"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun getTaskApi(id: String): Flow<DgfrResource<Task>> = loadingFlow {
        client.get(
            path = "workers/tasks/$id"
        )
    }
}
