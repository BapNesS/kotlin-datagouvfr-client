package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Notification
import com.baptistecarlier.kotlin.datagouvfr.client.api.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Notifications API
 */
internal interface NotificationsApi: WithApiKey {

    /**
     * List all current user pending notifications
     */
    fun getNotifications(): Flow<DgfrResource<List<Notification>>>
}