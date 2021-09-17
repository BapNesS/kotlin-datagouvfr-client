package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.models.*
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TransferApiImpl(private val client: HttpClient) : TransferApi {

    private val tag = "TransferApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getListTransfers(): Flow<List<Transfer>?> = flow {
        Log.d(tag, "getListTransfers / begin")
        val value = try {
            val response = client.get<List<Transfer>?>(
                path = "transfer/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getListTransfers / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postRequestTransfer(payload: TransferRequest): Flow<Transfer?> = flow {
        Log.d(tag, "postRequestTransfer / begin")
        val value = try {
            val response = client.post<Transfer?>(
                path = "transfer/"
            ) {
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postRequestTransfer / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun getTransfer(id: String): Flow<Transfer?> = flow {
        Log.d(tag, "getTransfer / begin")
        val value = try {
            val response = client.get<Transfer?>(
                path = "transfer/$id/"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getTransfer / Exception =  $e")
            null
        }
        emit(value)
    }

    override suspend fun postRespondToTransfer(
        id: String,
        payload: TransferResponse
    ): Flow<Transfer?> = flow {
        Log.d(tag, "postRespondToTransfer / begin")
        val value = try {
            val response = client.post<Transfer?>(
                path = "transfer/$id/"
            ) {
                contentType(ContentType.Application.Json)
                body = payload
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "postRespondToTransfer / Exception =  $e")
            null
        }
        emit(value)
    }

}
