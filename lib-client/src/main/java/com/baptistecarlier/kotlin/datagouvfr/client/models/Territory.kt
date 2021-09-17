package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Warning : may not be exaustive
 *
 * @property id
 * @property imageUrl
 * @property page
 * @property page
 * @property title
 */
@Serializable
data class Territory(
    @SerialName("id")
    var id: String,
    @SerialName("image_url")
    var imageUrl: String,
    @SerialName("page")
    var page: String,
    @SerialName("parent")
    var parent: String,
    @SerialName("title")
    var title: String,
)