package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property apikey The user API Key
 */
@Serializable
data class ApiKey(
    @SerialName("apikey")
    var apikey: String? = null
)