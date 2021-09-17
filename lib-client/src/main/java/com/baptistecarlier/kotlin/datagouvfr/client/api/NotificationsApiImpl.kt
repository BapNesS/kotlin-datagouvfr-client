package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.models.Notification
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class NotificationsApiImpl(private val client: HttpClient) : NotificationsApi {

    private val tag = "NotificationsApiImpl"

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getNotifications(): Flow<List<Notification>?> = flow {
        Log.d(tag, "getNotifications / begin")
        val value = try {
            val response = client.get<List<Notification>>(
                path = "notifications/"
            ) {
                addApiKey(apiKey)
            }
            response
        } catch (e: Exception) {
            Log.d(tag, "getNotifications / Exception =  $e")
            null
        }
        emit(value)
    }
}