package com.baptistecarlier.kotlin.datagouvfr.client

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
        val item = DgfrService(
            apiKey = "fake"
        )
        Assert.assertNotNull(item)
    }
}
