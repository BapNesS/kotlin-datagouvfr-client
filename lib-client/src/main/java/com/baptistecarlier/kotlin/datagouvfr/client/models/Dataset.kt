package com.baptistecarlier.kotlin.datagouvfr.client.models

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

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
    @SerialName("created_at")
    var createdAt: LocalDateTime,
    @SerialName("description")
    var description: String,
    @SerialName("frequency")
    var frequency: FrequencyEnum,
    @SerialName("last_modified")
    var lastModified: LocalDateTime,
    @SerialName("last_update")
    var lastUpdate: LocalDateTime,
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
    @SerialName("archived")
    var archived: LocalDateTime? = null,
    @SerialName("badges")
    var badges: List<Badge>? = null,
    @SerialName("community_resources")
    var communityResources: List<CommunityResource>? = null,
    @SerialName("deleted")
    var deleted: LocalDateTime? = null,
    //@SerialName("extras")
    //var extras: Map<String, Any?>? = null,
    @SerialName("featured")
    var featured: Boolean? = null,
    @SerialName("frequency_date")
    var frequencyDate: LocalDateTime? = null,
    @SerialName("id")
    var id: String? = null,
    @SerialName("license")
    var license: String? = null,
    @SerialName("metrics")
    var metrics: Metrics? = null,
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
    enum class FrequencyEnum {
        @SerialName("unknown")
        UNKNOWN,
        @SerialName("punctual")
        PUNCTUAL,
        @SerialName("continuous")
        CONTINUOUS,
        @SerialName("hourly")
        HOURLY,
        @SerialName("fourTimesADay")
        FOURTIMESADAY,
        @SerialName("threeTimesADay")
        THREETIMESADAY,
        @SerialName("semidaily")
        SEMIDAILY,
        @SerialName("daily")
        DAILY,
        @SerialName("fourTimesAWeek")
        FOURTIMESAWEEK,
        @SerialName("threeTimesAWeek")
        THREETIMESAWEEK,
        @SerialName("semiweekly")
        SEMIWEEKLY,
        @SerialName("weekly")
        WEEKLY,
        @SerialName("biweekly")
        BIWEEKLY,
        @SerialName("threeTimesAMonth")
        THREETIMESAMONTH,
        @SerialName("semimonthly")
        SEMIMONTHLY,
        @SerialName("monthly")
        MONTHLY,
        @SerialName("bimonthly")
        BIMONTHLY,
        @SerialName("quarterly")
        QUARTERLY,
        @SerialName("threeTimesAYear")
        THREETIMESAYEAR,
        @SerialName("semiannual")
        SEMIANNUAL,
        @SerialName("annual")
        ANNUAL,
        @SerialName("biennial")
        BIENNIAL,
        @SerialName("triennial")
        TRIENNIAL,
        @SerialName("quinquennial")
        QUINQUENNIAL,
        @SerialName("irregular")
        IRREGULAR

    }
}



