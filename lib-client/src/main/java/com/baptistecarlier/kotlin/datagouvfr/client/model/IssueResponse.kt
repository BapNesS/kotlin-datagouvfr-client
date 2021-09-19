package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property close Is this a closing response. Only subject owner can close
 * @property comment The comment to submit
 */
@Serializable
data class IssueResponse(
    @SerialName("comment")
    var comment: String,
    @SerialName("close")
    var close: Boolean? = null
)
