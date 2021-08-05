package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.serialization.Serializable
import kotlinx.serialization.SerialName

/**
 * @property acronym An optional dataset acronym
 * @property archived The archival date if archived
 * @property badges The dataset badges
 * @property communityResources
 * @property createdAt The dataset creation date
 * @property deleted The deletion date if deleted
 * @property description The dataset description in markdown
 * @property extras Extras attributes as key-value pairs
 * @property featured Is the dataset featured
 * @property frequency The update frequency
 * @property frequencyDate Next expected update date, you will be notified once that date is reached.
 * @property id The dataset identifier
 * @property lastModified The dataset last modification date
 * @property lastUpdate The resources last modification date
 * @property license The dataset license
 * @property metrics The dataset metrics
 * @property organization The producer organization
 * @property owner The user information
 * @property page The dataset page URL
 * @property `private` Is the dataset private to the owner or the organization
 * @property quality The dataset quality
 * @property resources
 * @property slug The dataset permalink string
 * @property spatial The spatial coverage
 * @property tags
 * @property temporalCoverage The temporal coverage
 * @property title The dataset title
 * @property uri The dataset API URI
 */
@Serializable
data class Dataset(
    //@SerialName("created_at")
    //var createdAt: ZonedDateTime,
    @SerialName("description")
    var description: String,
    @SerialName("frequency")
    var frequency: FrequencyEnum,
    //@SerialName("last_modified")
    //var lastModified: ZonedDateTime,
    //@SerialName("last_update")
    //var lastUpdate: ZonedDateTime,
    @SerialName("page")
    var page: String,
    @SerialName("slug")
    var slug: String,
    @SerialName("title")
    var title: String,
    @SerialName("uri")
    var uri: String,
    @SerialName("acronym")
    var acronym: String? = null,
    //@SerialName("archived")
    //var archived: ZonedDateTime? = null,
    @SerialName("badges")
    var badges: List<Badge>? = null,
    //@SerialName("community_resources")
    //var communityResources: List<Map<String, Any?>>? = null,
    //@SerialName("deleted")
    //var deleted: ZonedDateTime? = null,
    //@SerialName("extras")
    //var extras: Map<String, Any?>? = null,
    @SerialName("featured")
    var featured: Boolean? = null,
    //@SerialName("frequency_date")
    //var frequencyDate: ZonedDateTime? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("license")
    var license: String? = null,
    //@SerialName("metrics")
    //var metrics: Map<String, Any?>? = null,
    //@SerialName("organization")
    //var organization: Map<String, Any?>? = null,
    @SerialName("owner")
    var owner: User? = null,
    @SerialName("private")
    var private: Boolean? = null,
    //@SerialName("quality")
    //var quality: Map<String, Any?>? = null,
    //@SerialName("resources")
    //var resources: List<Map<String, Any?>>? = null,
    //@SerialName("spatial")
    //var spatial: Map<String, Any?>? = null,
    @SerialName("tags")
    var tags: List<String>? = null,
    //@SerialName("temporal_coverage")
    //var temporalCoverage: Map<String, Any?>? = null
) {
    /**
     * The update frequency
     * Values: UNKNOWN, PUNCTUAL, CONTINUOUS, HOURLY, FOURTIMESADAY, THREETIMESADAY, SEMIDAILY, DAILY, FOURTIMESAWEEK, THREETIMESAWEEK, SEMIWEEKLY, WEEKLY, BIWEEKLY, THREETIMESAMONTH, SEMIMONTHLY, MONTHLY, BIMONTHLY, QUARTERLY, THREETIMESAYEAR, SEMIANNUAL, ANNUAL, BIENNIAL, TRIENNIAL, QUINQUENNIAL, IRREGULAR
     */
    @Serializable
    enum class FrequencyEnum(val value: String) {
        @SerialName("unknown")
        UNKNOWN("unknown"),
        @SerialName("punctual")
        PUNCTUAL("punctual"),
        @SerialName("continuous")
        CONTINUOUS("continuous"),
        @SerialName("hourly")
        HOURLY("hourly"),
        @SerialName("fourTimesADay")
        FOURTIMESADAY("fourTimesADay"),
        @SerialName("threeTimesADay")
        THREETIMESADAY("threeTimesADay"),
        @SerialName("semidaily")
        SEMIDAILY("semidaily"),
        @SerialName("daily")
        DAILY("daily"),
        @SerialName("fourTimesAWeek")
        FOURTIMESAWEEK("fourTimesAWeek"),
        @SerialName("threeTimesAWeek")
        THREETIMESAWEEK("threeTimesAWeek"),
        @SerialName("semiweekly")
        SEMIWEEKLY("semiweekly"),
        @SerialName("weekly")
        WEEKLY("weekly"),
        @SerialName("biweekly")
        BIWEEKLY("biweekly"),
        @SerialName("threeTimesAMonth")
        THREETIMESAMONTH("threeTimesAMonth"),
        @SerialName("semimonthly")
        SEMIMONTHLY("semimonthly"),
        @SerialName("monthly")
        MONTHLY("monthly"),
        @SerialName("bimonthly")
        BIMONTHLY("bimonthly"),
        @SerialName("quarterly")
        QUARTERLY("quarterly"),
        @SerialName("threeTimesAYear")
        THREETIMESAYEAR("threeTimesAYear"),
        @SerialName("semiannual")
        SEMIANNUAL("semiannual"),
        @SerialName("annual")
        ANNUAL("annual"),
        @SerialName("biennial")
        BIENNIAL("biennial"),
        @SerialName("triennial")
        TRIENNIAL("triennial"),
        @SerialName("quinquennial")
        QUINQUENNIAL("quinquennial"),
        @SerialName("irregular")
        IRREGULAR("irregular")

    }
}
