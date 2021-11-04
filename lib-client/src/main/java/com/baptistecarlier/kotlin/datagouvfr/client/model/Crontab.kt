package com.baptistecarlier.kotlin.datagouvfr.client.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * @property dayOfMonth Cron expression for day of month
 * @property dayOfWeek Cron expression for day of week
 * @property hour Cron expression for hour
 * @property minute Cron expression for minute
 * @property monthOfYear Cron expression for month of year
 */
@Serializable
data class Crontab(
    @SerialName("day_of_month")
    var dayOfMonth: String,
    @SerialName("day_of_week")
    var dayOfWeek: String,
    @SerialName("hour")
    var hour: String,
    @SerialName("minute")
    var minute: String,
    @SerialName("month_of_year")
    var monthOfYear: String
)
