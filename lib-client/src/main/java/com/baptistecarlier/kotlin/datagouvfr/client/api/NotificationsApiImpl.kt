package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Notification
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

class NotificationsApiImpl(private val client: HttpClient) : NotificationsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override suspend fun getNotifications(): Flow<DgfrResource<List<Notification>>> = loadingFlow {
        client.get(
            path = "notifications/"
        ) {
            addApiKey(apiKey)
        }
    }
}