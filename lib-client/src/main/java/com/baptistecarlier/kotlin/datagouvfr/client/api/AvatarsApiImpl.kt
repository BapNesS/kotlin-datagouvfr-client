package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow

internal class AvatarsApiImpl(private val client: HttpClient) : AvatarsApi {

    override fun getAvatar(identifier: String, size: Int): Flow<DgfrResource<ByteArray>> = loadingFlow {
        val httpResponse: HttpResponse = client.get(path = "avatars/$identifier/$size")
        val byteArrayBody: ByteArray = httpResponse.receive()
        byteArrayBody
    }
}
