package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.WorkersApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockBoolean
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockJob
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockTask
import com.baptistecarlier.kotlin.datagouvfr.client.model.Job
import com.baptistecarlier.kotlin.datagouvfr.client.model.Task
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class WorkersApiImplTest {

    private lateinit var apiImpl: WorkersApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = WorkersApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = WorkersApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListJobs

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListJobs when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListJobs()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Job>>)
        assert(results[1] is DgfrCallState.ClientError<List<Job>>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListJobs when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Job>())

        val flow = apiImpl.getListJobs()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Job>>)
        assert(results[1] is DgfrCallState.ServerError<List<Job>>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListJobs when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Job>())

        val flow = apiImpl.getListJobs()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<Job>>)
        assert(results[1] is DgfrCallState.Success<List<Job>>)
    }

    // endregion getListJobs

    // region postJobsApi

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `postJobsApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postJobsApi(mockJob)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.ClientError<Job>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `postJobsApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockJob)

        val flow = apiImpl.postJobsApi(mockJob)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.ServerError<Job>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `postJobsApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockJob)

        val flow = apiImpl.postJobsApi(mockJob)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.Success<Job>)
    }

    // endregion postJobsApi

    // region getJobsReferenceApi

    @Test
    fun `getJobsReferenceApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getJobsReferenceApi()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.ClientError<List<String>>)
    }

    @Test
    fun `getJobsReferenceApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<String>())

        val flow = apiImpl.getJobsReferenceApi()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.ServerError<List<String>>)
    }

    @Test
    fun `getJobsReferenceApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<String>())

        val flow = apiImpl.getJobsReferenceApi()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<List<String>>)
        assert(results[1] is DgfrCallState.Success<List<String>>)
    }

    // endregion getJobsReferenceApi

    // region deleteJobApi

    @Test
    fun `deleteJobApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ClientError<Boolean>)
    }

    @Test
    fun `deleteJobApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.ServerError<Boolean>)
    }

    @Test
    fun `deleteJobApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Boolean>)
        assert(results[1] is DgfrCallState.Success<Boolean>)
    }

    // endregion deleteJobApi

    // region getJobApi

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getJobApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.ClientError<Job>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getJobApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockJob)

        val flow = apiImpl.getJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.ServerError<Job>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getJobApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockJob)

        val flow = apiImpl.getJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.Success<Job>)
    }

    // endregion getJobApi

    // region putJobApi

    @Test
    @OptIn(MissingFieldMapping::class)
    fun `putJobApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.ClientError<Job>)
    }

    @Test
    @OptIn(MissingFieldMapping::class)
    fun `putJobApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockJob)

        val flow = apiImpl.putJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.ServerError<Job>)
    }

    @Test
    @OptIn(MissingFieldMapping::class)
    fun `putJobApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockJob)

        val flow = apiImpl.putJobApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Job>)
        assert(results[1] is DgfrCallState.Success<Job>)
    }

    // endregion putJobApi

    // region getTaskApi

    @Test
    fun `getTaskApi when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getTaskApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Task>)
        assert(results[1] is DgfrCallState.ClientError<Task>)
    }

    @Test
    fun `getTaskApi when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockTask)

        val flow = apiImpl.getTaskApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Task>)
        assert(results[1] is DgfrCallState.ServerError<Task>)
    }

    @Test
    fun `getTaskApi when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockTask)

        val flow = apiImpl.getTaskApi("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrCallState.Loading<Task>)
        assert(results[1] is DgfrCallState.Success<Task>)
    }

    // endregion getTaskApi
}
