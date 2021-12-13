package com.baptistecarlier.kotlin.datagouvfr.client.model

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property coordinates The geometry as coordinates lists
 * @property typeEnum The GeoJSON Type
 */
@MissingFieldMapping
@Serializable
data class GeoJSON(
    /*@SerialName("coordinates")
    var coordinates: List<Map<String, Any?>>,*/
    @SerialName("type")
    var typeEnum: GeoJSON.TypeEnum
) {
    /**
     * The GeoJSON Type
     * Values: POINT, LINESTRING, POLYGON, MULTIPOINT, MULTILINESTRING, MULTIPOLYGON
     */
    @Serializable
    enum class TypeEnum(val value: String) {
        @SerialName("Point")
        POINT("Point"),
        @SerialName("LineString")
        LINESTRING("LineString"),
        @SerialName("Polygon")
        POLYGON("Polygon"),
        @SerialName("MultiPoint")
        MULTIPOINT("MultiPoint"),
        @SerialName("MultiLineString")
        MULTILINESTRING("MultiLineString"),
        @SerialName("MultiPolygon")
        MULTIPOLYGON("MultiPolygon")
    }
}
