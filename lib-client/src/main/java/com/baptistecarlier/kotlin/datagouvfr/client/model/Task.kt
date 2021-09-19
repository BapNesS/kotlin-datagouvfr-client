package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property exc The exception thrown during execution
 * @property id Tha task execution ID
 * @property result The task results if exists
 * @property status Cron expression for hour
 * @property traceback The execution traceback
 */
@kotlinx.serialization.Serializable
data class Task(
    @SerialName("exc")
    var exc: String? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("result")
    var result: String? = null,
    @SerialName("status")
    var status: Task.StatusEnum? = null,
    @SerialName("traceback")
    var traceback: String? = null
) {
    /**
     * Cron expression for hour
     * Values: STARTED, RECEIVED, PENDING, FAILURE, RETRY, SUCCESS, REVOKED
     */
    @Serializable
    enum class StatusEnum(val value: String) {
        @SerialName("STARTED")
        STARTED("STARTED"),
        @SerialName("RECEIVED")
        RECEIVED("RECEIVED"),
        @SerialName("PENDING")
        PENDING("PENDING"),
        @SerialName("FAILURE")
        FAILURE("FAILURE"),
        @SerialName("RETRY")
        RETRY("RETRY"),
        @SerialName("SUCCESS")
        SUCCESS("SUCCESS"),
        @SerialName("REVOKED")
        REVOKED("REVOKED")
    }
}