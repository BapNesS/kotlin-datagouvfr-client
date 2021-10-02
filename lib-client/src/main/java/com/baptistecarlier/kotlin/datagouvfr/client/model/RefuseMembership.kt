package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property comment The refusal comment.
 */
@Serializable
data class RefuseMembership(
    @SerialName("comment")
    var comment: String? = null
)