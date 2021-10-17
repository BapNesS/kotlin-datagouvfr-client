package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

class TransferApiImpl(private val client: HttpClient) : TransferApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun getListTransfers(): Flow<DgfrResource<List<Transfer>>> = loadingFlow {
        client.get(
            path = "transfer/"
        )
    }

    override fun postRequestTransfer(payload: TransferRequest): Flow<DgfrResource<Transfer>> = loadingFlow {
        client.post(
            path = "transfer/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getTransfer(id: String): Flow<DgfrResource<Transfer>> = loadingFlow {
        client.get(
            path = "transfer/$id/"
        )
    }

    override fun postRespondToTransfer(
        id: String,
        payload: TransferResponse
    ): Flow<DgfrResource<Transfer>> = loadingFlow {
        client.post(
            path = "transfer/$id/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

}
