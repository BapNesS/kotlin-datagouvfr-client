package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property features
 * @property typeEnum
 */
@Serializable
data class GeoJSONFeatureCollection(
    @SerialName("features")
    var features: List<GeoJSONFeature>,
    @SerialName("type")
    var typeEnum: GeoJSONFeatureCollection.TypeEnum
) {
    /**
     * Values: FEATURECOLLECTION
     */
    @Serializable
    enum class TypeEnum(val value: String) {
        @SerialName("FeatureCollection")
        FEATURECOLLECTION("FeatureCollection")
    }
}