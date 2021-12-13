package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Notification
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

internal class NotificationsApiImpl(private val client: HttpClient) : NotificationsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    override fun getNotifications(): Flow<DgfrCallState<List<Notification>>> = loadingFlow {
        client.get(
            path = "notifications/"
        ) {
            addApiKey(apiKey)
        }
    }
}
