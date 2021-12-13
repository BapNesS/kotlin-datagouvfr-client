package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class TransferApiImpl(private val client: HttpClient) : TransferApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun getListTransfers(): Flow<DgfrCallState<List<Transfer>>> = loadingFlow {
        client.get(
            path = "transfer/"
        )
    }

    override fun postRequestTransfer(payload: TransferRequest): Flow<DgfrCallState<Transfer>> = loadingFlow {
        client.post(
            path = "transfer/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun getTransfer(id: String): Flow<DgfrCallState<Transfer>> = loadingFlow {
        client.get(
            path = "transfer/$id/"
        )
    }

    override fun postRespondToTransfer(
        id: String,
        payload: TransferResponse
    ): Flow<DgfrCallState<Transfer>> = loadingFlow {
        client.post(
            path = "transfer/$id/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }
}
