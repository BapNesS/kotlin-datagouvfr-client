package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.IssuesApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockIssue
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockIssuePage
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockIssueResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.Issue
import com.baptistecarlier.kotlin.datagouvfr.client.model.IssuePage
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class IssuesApiImplTest {

    private lateinit var apiImpl: IssuesApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = IssuesApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = IssuesApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListIssues

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListIssues when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListIssues()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<IssuePage>)
        assert(results[1] is DgfrResource.ClientError<IssuePage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListIssues when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockIssuePage)

        val flow = apiImpl.getListIssues()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<IssuePage>)
        assert(results[1] is DgfrResource.ServerError<IssuePage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListIssues when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockIssuePage)

        val flow = apiImpl.getListIssues()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<IssuePage>)
        assert(results[1] is DgfrResource.Success<IssuePage>)
    }

    // endregion getListIssues

    // region postCreateIssue

    @Test
    fun `postCreateIssue when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateIssue(mockIssue)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.ClientError<Issue>)
    }

    @Test
    fun `postCreateIssue when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockIssue)

        val flow = apiImpl.postCreateIssue(mockIssue)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.ServerError<Issue>)
    }

    @Test
    fun `postCreateIssue when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockIssue)

        val flow = apiImpl.postCreateIssue(mockIssue)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.Success<Issue>)
    }

    // endregion postCreateIssue

    // region getIssue

    @Test
    fun `getIssue when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getIssue("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.ClientError<Issue>)
    }

    @Test
    fun `getIssue when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockIssue)

        val flow = apiImpl.getIssue("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.ServerError<Issue>)
    }

    @Test
    fun `getIssue when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockIssue)

        val flow = apiImpl.getIssue("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.Success<Issue>)
    }

    // endregion getIssue

    // region postCommentIssue

    @Test
    fun `postCommentIssue when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCommentIssue("", mockIssueResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.ClientError<Issue>)
    }

    @Test
    fun `postCommentIssue when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockIssue)

        val flow = apiImpl.postCommentIssue("", mockIssueResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.ServerError<Issue>)
    }

    @Test
    fun `postCommentIssue when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockIssue)

        val flow = apiImpl.postCommentIssue("", mockIssueResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Issue>)
        assert(results[1] is DgfrResource.Success<Issue>)
    }

    // endregion postCommentIssue
}
