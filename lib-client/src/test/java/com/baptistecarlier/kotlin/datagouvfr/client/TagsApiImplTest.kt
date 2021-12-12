package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.TagsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.model.Tag
import io.ktor.client.request.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class TagsApiImplTest {

    private lateinit var apiImpl: TagsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = TagsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = TagsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getTagsSuggest

    @Test
    fun `getTagsSuggest when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getTagsSuggest("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Tag>>)
        assert(results[1] is DgfrResource.ClientError<List<Tag>>)
    }

    @Test
    fun `getTagsSuggest when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Tag>())

        val flow = apiImpl.getTagsSuggest("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Tag>>)
        assert(results[1] is DgfrResource.ServerError<List<Tag>>)
    }

    @Test
    fun `getTagsSuggest when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Tag>())

        val flow = apiImpl.getTagsSuggest("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Tag>>)
        assert(results[1] is DgfrResource.Success<List<Tag>>)
    }

    // endregion getTagsSuggest
}
