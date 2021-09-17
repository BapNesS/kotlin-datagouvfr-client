package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Warning : may not be exaustive
 *
 * @property height
 * @property html
 * @property maxheight
 * @property maxwidth
 * @property type
 * @property version
 * @property width
 */
@Serializable
data class Oembed(
    @SerialName("height")
    var height: Int,
    @SerialName("html")
    var html: String,
    @SerialName("maxheight")
    var maxheight: Int,
    @SerialName("maxwidth")
    var maxwidth: Int,
    @SerialName("type")
    var type: String,
    @SerialName("version")
    var version: String,
    @SerialName("width")
    var width: Int
)