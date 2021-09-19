package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property name The role name
 */
@Serializable
data class UserRole(
    @SerialName("name")
    var name: String? = null
)
