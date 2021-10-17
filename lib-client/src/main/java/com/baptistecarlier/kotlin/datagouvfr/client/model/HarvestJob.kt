package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property created The job creation date
 * @property ended The job end date
 * @Serializable
 * @property id The job execution ID
 * @property items The job collected items
 * @property source The source owning the job
 * @property started The job start date
 * @property status The job status
 */
@Serializable
data class HarvestJob(
    @SerialName("created")
    var created: LocalDateTime,
    @SerialName("id")
    var id: String,
    @SerialName("source")
    var source: String,
    @SerialName("status")
    var status: HarvestJobStatusEnum,
    @SerialName("ended")
    var ended: LocalDateTime? = null,
    @SerialName("errors")
    var errors: List<HarvestError>? = null,
    @SerialName("items")
    var items: List<HarvestItem>? = null,
    @SerialName("started")
    var started: LocalDateTime? = null
)