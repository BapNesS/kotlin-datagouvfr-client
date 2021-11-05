package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property by Who performed the validation
 * @property comment A comment about the validation. Required on rejection
 * @property on Date date on which validation was performed
 * @property state Is it validated or not
 */
@Serializable
data class HarvestSourceValidation(
    @SerialName("state")
    var state: HarvestSourceValidation.StateEnum,
/*    @SerialName("by")
    var by: Map<String, Any?>? = null,*/
    @SerialName("comment")
    var comment: String? = null,
    @SerialName("on")
    var on: LocalDateTime? = null
) {
    /**
     * Is it validated or not
     * Values: PENDING, ACCEPTED, REFUSED
     */
    @Serializable
    enum class StateEnum(val value: String) {
        @SerialName("pending") PENDING("pending"),
        @SerialName("accepted") ACCEPTED("accepted"),
        @SerialName("refused") REFUSED("refused")
    }
}
