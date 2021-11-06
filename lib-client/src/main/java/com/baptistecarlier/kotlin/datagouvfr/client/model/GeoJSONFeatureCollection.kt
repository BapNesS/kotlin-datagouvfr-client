package com.baptistecarlier.kotlin.datagouvfr.client.model

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property features
 * @property typeEnum
 */
@Serializable
@OptIn(MissingFieldMapping::class)
data class GeoJSONFeatureCollection constructor(
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
