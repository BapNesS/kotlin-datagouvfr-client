package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


/**
 * @property created The job creation date
 * @property ended The job end date
 * @property errors The job initialization errors
 * @property id The job execution ID
 * @property items The job collected items
 * @property source The source owning the job
 * @property started The job start date
 * @property status The job status
 */
@Serializable
data class HarvestJobPreview(
    @SerialName("created")
    var created: LocalDateTime,
    @SerialName("id")
    var id: String,
    @SerialName("source")
    var source: String,
    @SerialName("status")
    var status: HarvestJobPreview.StatusEnum,
    @SerialName("ended")
    var ended: LocalDateTime? = null,
    @SerialName("errors")
    var errors: List<HarvestError>? = null,
    @SerialName("items")
    var items: List<HarvestItemPreview>? = null,
    @SerialName("started")
    var started: LocalDateTime? = null
) {
    /**
     * The job status
     * Values: PENDING, INITIALIZING, INITIALIZED, PROCESSING, DONE, DONEMINUSERRORS, FAILED
     */
    @Serializable
    enum class StatusEnum(val value: String) {
        @SerialName("pending") PENDING("pending"),
        @SerialName("initializing") INITIALIZING("initializing"),
        @SerialName("initialized") INITIALIZED("initialized"),
        @SerialName("processing") PROCESSING("processing"),
        @SerialName("done") DONE("done"),
        @SerialName("done-errors") DONEMINUSERRORS("done-errors"),
        @SerialName("failed") FAILED("failed")
    }
}