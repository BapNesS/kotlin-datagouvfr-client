package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Harvest item status
 * Values: PENDING, STARTED, DONE, FAILED, SKIPPED, ARCHIVED
 */
@Serializable
enum class HarvestItemStatusEnum(val value: String) {
    @SerialName("pending") PENDING("pending"),
    @SerialName("started") STARTED("started"),
    @SerialName("done") DONE("done"),
    @SerialName("failed") FAILED("failed"),
    @SerialName("skipped") SKIPPED("skipped"),
    @SerialName("archived") ARCHIVED("archived")
}
