package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.DiscussionsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockDiscussion
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockDiscussionPage
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockDiscussionStart
import com.baptistecarlier.kotlin.datagouvfr.client.model.Discussion
import com.baptistecarlier.kotlin.datagouvfr.client.model.DiscussionPage
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class DiscussionsApiImplTest {

    private lateinit var apiImpl: DiscussionsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = DiscussionsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = DiscussionsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListDiscussions

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListDiscussions when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListDiscussions()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<DiscussionPage>)
        assert(results[1] is DgfrCallState.ClientError<DiscussionPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListDiscussions when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDiscussionPage)

        val flow = apiImpl.getListDiscussions()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<DiscussionPage>)
        assert(results[1] is DgfrCallState.ServerError<DiscussionPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListDiscussions when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDiscussionPage)

        val flow = apiImpl.getListDiscussions()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<DiscussionPage>)
        assert(results[1] is DgfrCallState.Success<DiscussionPage>)
    }

    // endregion getListDiscussions

    // region postCreateDiscussion

    @Test
    fun `postCreateDiscussion when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateDiscussion(mockDiscussionStart)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.ClientError<Discussion>)
    }

    @Test
    fun `postCreateDiscussion when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDiscussion)

        val flow = apiImpl.postCreateDiscussion(mockDiscussionStart)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.ServerError<Discussion>)
    }

    @Test
    fun `postCreateDiscussion when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDiscussion)

        val flow = apiImpl.postCreateDiscussion(mockDiscussionStart)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.Success<Discussion>)
    }

    // endregion postCreateDiscussion

    // region deleteDiscussion

    @Test
    fun `deleteDiscussion when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteDiscussion("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteDiscussion when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteDiscussion("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteDiscussion when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteDiscussion("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteDiscussion

    // region getDiscussion

    @Test
    fun `getDiscussion when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getDiscussion("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.ClientError<Discussion>)
    }

    @Test
    fun `getDiscussion when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDiscussion)

        val flow = apiImpl.getDiscussion("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.ServerError<Discussion>)
    }

    @Test
    fun `getDiscussion when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDiscussion)

        val flow = apiImpl.getDiscussion("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.Success<Discussion>)
    }

    // endregion getDiscussion

    // region postCommentDiscussion

    @Test
    fun `postCommentDiscussion when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCommentDiscussion("", mockDiscussionResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.ClientError<Discussion>)
    }

    @Test
    fun `postCommentDiscussion when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDiscussion)

        val flow = apiImpl.postCommentDiscussion("", mockDiscussionResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.ServerError<Discussion>)
    }

    @Test
    fun `postCommentDiscussion when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDiscussion)

        val flow = apiImpl.postCommentDiscussion("", mockDiscussionResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Discussion>)
        assert(results[1] is DgfrCallState.Success<Discussion>)
    }

    // endregion postCommentDiscussion

    // region deleteDiscussionComment

    @Test
    fun `deleteDiscussionComment when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteDiscussionComment("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteDiscussionComment when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteDiscussionComment("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteDiscussionComment when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteDiscussionComment("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteDiscussionComment"
}
