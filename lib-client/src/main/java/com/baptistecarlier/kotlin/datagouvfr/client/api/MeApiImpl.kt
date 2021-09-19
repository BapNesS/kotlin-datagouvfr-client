package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.User
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class MeApiImpl(private val client: HttpClient) : MeApi {

    private val tag = "UserApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun me(): Flow<User?> = flow {
        Log.d(tag, "me / begin")
        val value = try {
            val response = client.get<User>(
                path = "me/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "me / Exception =  $e")
            null
        }
        emit(value)
    }
}