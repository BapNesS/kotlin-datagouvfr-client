package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import kotlinx.datetime.*
import org.junit.Assert
import org.junit.Test

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
    @Test
    fun tmp_test_2() {
        val mockClient = HttpClient()
        val siteApiImpl = SiteApiImpl(mockClient)
        siteApiImpl.getHomeReuses()
        siteApiImpl.getSite()
        Assert.assertNotNull(siteApiImpl)
    }
    @Test
    fun tmp_test_3() {
        val a = loadingFlow {
            Assert.assertNotNull("a")
        }
    }
}
