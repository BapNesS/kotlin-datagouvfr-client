package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property args The item positional arguments
 * @property created The item creation date
 * @property dataset The processed dataset
 * @property ended The item end date
 * @property errors The item errors
 * @property kwargs The item keyword arguments
 * @property remoteId The item remote ID to process
 * @property started The item start date
 * @property status The item status
 */
@Serializable
data class HarvestItemPreview(
    @SerialName("created")
    var created: LocalDateTime,
    @SerialName("remote_id")
    var remoteId: String,
    @SerialName("status")
    var status: HarvestItemStatusEnum,
    @SerialName("args")
    var args: List<String>? = null,
/*    @SerialName("dataset")
    var dataset: Map<String, Any?>? = null,*/
    @SerialName("ended")
    var ended: LocalDateTime? = null,
    @SerialName("errors")
    var errors: List<HarvestError>? = null,
/*    @SerialName("kwargs")
    var kwargs: Map<String, Any?>? = null,*/
    @SerialName("started")
    var started: LocalDateTime? = null
)
