package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class AvatarsApiImpl(private val client: HttpClient) : AvatarsApi {

    private val tag = "AvatarsApiImpl"

    override suspend fun getAvatar(identifier: String, size: Int): Flow<ByteArray?> = flow {
        val value = try {
            Log.d(tag, "getAvatar / begin")

            val httpResponse: HttpResponse = client.get(
                path = "avatars/$identifier/$size")
            val byteArrayBody: ByteArray = httpResponse.receive()
            Log.d(tag, "getAvatar / response = $byteArrayBody")

            byteArrayBody
        } catch (e: Exception) {
            Log.d(tag, "getAvatar / Exception =  $e")
            null
        }
        emit(value)
    }
}