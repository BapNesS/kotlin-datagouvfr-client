package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property fips
 * @property geonames
 * @property iso2
 * @property iso3
 * @property osm
 * @property un
 * @property histo
 * @property insee
 * @property postal
 * @property siren
 */
@Serializable
data class TerritorySuggestionKey(
    @SerialName("fips")
    var fips: String? = null,
    @SerialName("geonames")
    var geonames: String? = null,
    @SerialName("iso2")
    var iso2: String? = null,
    @SerialName("iso3")
    var iso3: String? = null,
    @SerialName("osm")
    var osm: String? = null,
    @SerialName("un")
    var un: String? = null,
    @SerialName("histo")
    var histo: String? = null,
    @SerialName("insee")
    var insee: String? = null,
    @SerialName("postal")
    var postal: List<String>? = null,
    @SerialName("siren")
    var siren: String? = null
)