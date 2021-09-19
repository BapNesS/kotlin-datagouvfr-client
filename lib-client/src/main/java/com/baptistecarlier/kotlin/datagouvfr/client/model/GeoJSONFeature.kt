package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property geometry
 * @property id
 * @property properties
 * @property typeEnum
 */
@Serializable
data class GeoJSONFeature(
    @SerialName("geometry")
    var geometry: GeoJSON,
    @SerialName("type")
    var typeEnum: GeoJSONFeature.TypeEnum,
    @SerialName("id")
    var id: String? = null,
    /*@SerialName("properties")
    var properties: Map<String, Any?>? = null*/
) {
    /**
     * Values: FEATURE
     */
    @Serializable
    enum class TypeEnum(val value: String) {
        @SerialName("Feature")
        FEATURE("Feature")
    }
}