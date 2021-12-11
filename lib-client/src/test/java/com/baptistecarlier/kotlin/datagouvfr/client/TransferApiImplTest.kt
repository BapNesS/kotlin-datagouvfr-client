package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.api.TransferApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockTransfer
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockTransferRequest
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockTransferResponse
import com.baptistecarlier.kotlin.datagouvfr.client.model.Transfer
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class TransferApiImplTest {

    private lateinit var apiImpl: TransferApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = TransferApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = TransferApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getTransfer

    @Test
    fun `getTransfer when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getTransfer("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.ClientError<Transfer>)
    }

    @Test
    fun `getTransfer when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTransfer)

        val flow = apiImpl.getTransfer("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.ServerError<Transfer>)
    }

    @Test
    fun `getTransfer when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTransfer)

        val flow = apiImpl.getTransfer("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.Success<Transfer>)
    }

    // endregion getTransfer

    // region getListTransfers

    @Test
    fun `getListTransfers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListTransfers()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Transfer>>)
        assert(results[1] is DgfrResource.ClientError<List<Transfer>>)
    }

    @Test
    fun `getListTransfers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Transfer>())

        val flow = apiImpl.getListTransfers()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Transfer>>)
        assert(results[1] is DgfrResource.ServerError<List<Transfer>>)
    }

    @Test
    fun `getListTransfers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Transfer>())

        val flow = apiImpl.getListTransfers()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Transfer>>)
        assert(results[1] is DgfrResource.Success<List<Transfer>>)
    }

    // endregion getListTransfers

    // region postRequestTransfer

    @Test
    fun `postRequestTransfer when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postRequestTransfer(mockTransferRequest)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.ClientError<Transfer>)
    }

    @Test
    fun `postRequestTransfer when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTransfer)

        val flow = apiImpl.postRequestTransfer(mockTransferRequest)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.ServerError<Transfer>)
    }

    @Test
    fun `postRequestTransfer when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTransfer)

        val flow = apiImpl.postRequestTransfer(mockTransferRequest)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.Success<Transfer>)
    }

    // endregion postRequestTransfer

    // region postRespondToTransfer

    @Test
    fun `postRespondToTransfer when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postRespondToTransfer("", mockTransferResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.ClientError<Transfer>)
    }

    @Test
    fun `postRespondToTransfer when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTransfer)

        val flow = apiImpl.postRespondToTransfer("", mockTransferResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.ServerError<Transfer>)
    }

    @Test
    fun `postRespondToTransfer when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTransfer)

        val flow = apiImpl.postRespondToTransfer("", mockTransferResponse)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Transfer>)
        assert(results[1] is DgfrResource.Success<Transfer>)
    }

    // endregion postRespondToTransfer
}
