package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
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

    @OptIn(MissingFieldMapping::class)
    override fun getListJobs(): Flow<DgfrCallState<List<Job>>> = loadingFlow {
        client.get(
            path = "workers/jobs/"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun postJobsApi(payload: Job): Flow<DgfrCallState<Job>> = loadingFlow {
        client.post(
            path = "workers/jobs/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getJobsReferenceApi(): Flow<DgfrCallState<List<String>>> = loadingFlow {
        client.get(
            path = "workers/jobs/schedulables/"
        )
    }

    override fun deleteJobApi(id: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "workers/jobs/$id"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    @OptIn(MissingFieldMapping::class)
    override fun getJobApi(id: String): Flow<DgfrCallState<Job>> = loadingFlow {
        client.get(
            path = "workers/jobs/$id"
        )
    }

    @OptIn(MissingFieldMapping::class)
    override fun putJobApi(id: String): Flow<DgfrCallState<Job>> = loadingFlow {
        client.put(
            path = "workers/jobs/$id"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun getTaskApi(id: String): Flow<DgfrCallState<Task>> = loadingFlow {
        client.get(
            path = "workers/tasks/$id"
        )
    }
}
