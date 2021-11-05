package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.util.date.*
import kotlinx.datetime.*
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun tmp_test() {
        val currentMoment: Instant = Clock.System.now()
        val datetimeInUtc: LocalDateTime = currentMoment.toLocalDateTime(TimeZone.UTC)

        val item = Dataset(
            createdAt = datetimeInUtc,
            description = "String",
            frequency = Dataset.FrequencyEnum.ANNUAL,
            lastModified = datetimeInUtc,
            lastUpdate = datetimeInUtc,
            page = "String",
            slug = "String",
            title = "String",
            uri = "String",

        )
        Assert.assertNotNull(item)
    }
}
