package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property `class` The object class
 * @property id The object unique identifier
 * @property acronym An optional dataset acronym
 * @property page The web page URL for this dataset
 * @property title The dataset title
 * @property uri The API URI for this dataset
 */
@Serializable
data class DatasetReference(
    @SerialName("class")
    var `class`: String,
    @SerialName("id")
    var id: String,
    @SerialName("acronym")
    var acronym: String? = null,
    @SerialName("page")
    var page: String? = null,
    @SerialName("title")
    var title: String? = null,
    @SerialName("uri")
    var uri: String? = null
)