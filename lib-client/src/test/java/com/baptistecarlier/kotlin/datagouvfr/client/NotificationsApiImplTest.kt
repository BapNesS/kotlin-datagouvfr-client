package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.NotificationsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.model.Notification
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class NotificationsApiImplTest {

    private lateinit var apiImpl: NotificationsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = NotificationsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = NotificationsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getNotifications

    @Test
    fun `getNotifications when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getNotifications()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Notification>>)
        assert(results[1] is DgfrResource.ClientError<List<Notification>>)
    }

    @Test
    fun `getNotifications when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Notification>())

        val flow = apiImpl.getNotifications()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Notification>>)
        assert(results[1] is DgfrResource.ServerError<List<Notification>>)
    }

    @Test
    fun `getNotifications when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Notification>())

        val flow = apiImpl.getNotifications()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Notification>>)
        assert(results[1] is DgfrResource.Success<List<Notification>>)
    }

    // endregion getNotifications
}
