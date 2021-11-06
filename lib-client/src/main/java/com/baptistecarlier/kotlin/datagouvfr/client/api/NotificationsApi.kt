package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.model.Notification
import kotlinx.coroutines.flow.Flow

/**
 * Notifications API
 */
internal interface NotificationsApi : WithApiKey {

    /**
     * List all current user pending notifications
     */
    fun getNotifications(): Flow<DgfrCallState<List<Notification>>>
}
