package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.TopicsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockBoolean
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockTopic
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockTopicPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.Topic
import com.baptistecarlier.kotlin.datagouvfr.client.model.TopicPage
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class TopicsApiImplTest {

    private lateinit var apiImpl: TopicsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = TopicsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = TopicsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListTopics

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListTopics when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListTopics()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<TopicPage>)
        assert(results[1] is DgfrCallState.ClientError<TopicPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListTopics when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTopicPage)

        val flow = apiImpl.getListTopics()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<TopicPage>)
        assert(results[1] is DgfrCallState.ServerError<TopicPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListTopics when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTopicPage)

        val flow = apiImpl.getListTopics()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<TopicPage>)
        assert(results[1] is DgfrCallState.Success<TopicPage>)
    }

    // endregion getListTopics

    // region postCreateTopic

    @Test
    fun `postCreateTopic when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateTopic(mockTopic)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.ClientError<Topic>)
    }

    @Test
    fun `postCreateTopic when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTopic)

        val flow = apiImpl.postCreateTopic(mockTopic)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.ServerError<Topic>)
    }

    @Test
    fun `postCreateTopic when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTopic)

        val flow = apiImpl.postCreateTopic(mockTopic)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.Success<Topic>)
    }

    // endregion postCreateTopic

    // region deleteTopic

    @Test
    fun `deleteTopic when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteTopic("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteTopic when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteTopic("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteTopic when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteTopic("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteTopic

    // region getTopic

    @Test
    fun `getTopic when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getTopic("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.ClientError<Topic>)
    }

    @Test
    fun `getTopic when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTopic)

        val flow = apiImpl.getTopic("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.ServerError<Topic>)
    }

    @Test
    fun `getTopic when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTopic)

        val flow = apiImpl.getTopic("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.Success<Topic>)
    }

    // endregion getTopic

    // region putUpdateTopic

    @Test
    fun `putUpdateTopic when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateTopic("", mockTopic)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.ClientError<Topic>)
    }

    @Test
    fun `putUpdateTopic when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTopic)

        val flow = apiImpl.putUpdateTopic("", mockTopic)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.ServerError<Topic>)
    }

    @Test
    fun `putUpdateTopic when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTopic)

        val flow = apiImpl.putUpdateTopic("", mockTopic)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Topic>)
        assert(results[1] is DgfrCallState.Success<Topic>)
    }

    // endregion putUpdateTopic
}
