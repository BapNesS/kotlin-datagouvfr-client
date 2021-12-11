package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource.*
import com.baptistecarlier.kotlin.datagouvfr.client.api.AvatarsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertEquals
import org.junit.Test

internal class AvatarsApiImplTest {

    private val apiMockEngine = ApiMockEngine()
    private lateinit var apiImpl: AvatarsApiImpl

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = AvatarsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = AvatarsApiImpl(apiMockEngine.error())
    }

    // region getAvatar

    @Test
    fun `getAvatar when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getAvatar("", 0)

        val results = flow.toList()
        assertEquals(results.size, 2)
        assert(results[0] is Loading<ByteArray>)
        assert(results[1] is ClientError<ByteArray>)
    }

    @Test
    fun `getAvatar when Http BadRequest then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, ByteArray(0))

        val flow = apiImpl.getAvatar("", 0)

        val results = flow.toList()
        assertEquals(results.size, 2)
        assert(results[0] is Loading<ByteArray>)
        assert(results[1] is ServerError<ByteArray>)
        assertEquals((results[1] as ServerError).httpCode, HttpStatusCode.BadRequest.value)
    }

    @Test
    fun `getAvatar when Http OK then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, ByteArray(0))

        val flow = apiImpl.getAvatar("", 0)

        val results = flow.toList()
        assertEquals(results.size, 2)
        assert(results[0] is Loading<ByteArray>)
        assert(results[1] is Success<ByteArray>)
    }

    // endregion getAvatar
}
