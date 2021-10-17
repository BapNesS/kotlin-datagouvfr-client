package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * The job status
 * Values: PENDING, INITIALIZING, INITIALIZED, PROCESSING, DONE, DONEMINUSERRORS, FAILED
 */
@Serializable
enum class HarvestJobStatusEnum(val value: String) {
    @SerialName("pending") PENDING("pending"),
    @SerialName("initializing") INITIALIZING("initializing"),
    @SerialName("initialized") INITIALIZED("initialized"),
    @SerialName("processing") PROCESSING("processing"),
    @SerialName("done") DONE("done"),
    @SerialName("done-errors") DONEMINUSERRORS("done-errors"),
    @SerialName("failed") FAILED("failed")
}