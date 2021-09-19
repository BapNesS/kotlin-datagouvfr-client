package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.Notification
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Notifications API
 */
interface NotificationsApi: WithApiKey {

    /**
     * List all current user pending notifications
     */
    suspend fun getNotifications(): Flow<List<Notification>?>
}