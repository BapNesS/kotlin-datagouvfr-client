package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.OrganizationsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class OrganizationsApiImplTest {

    private lateinit var apiImpl: OrganizationsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = OrganizationsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = OrganizationsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListOrganizations

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizations when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListOrganizations()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<OrganizationPage>)
        assert(results[1] is DgfrResource.ClientError<OrganizationPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizations when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockOrganizationPage)

        val flow = apiImpl.getListOrganizations()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<OrganizationPage>)
        assert(results[1] is DgfrResource.ServerError<OrganizationPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizations when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockOrganizationPage)

        val flow = apiImpl.getListOrganizations()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<OrganizationPage>)
        assert(results[1] is DgfrResource.Success<OrganizationPage>)
    }

    // endregion getListOrganizations

    // region postCreateOrganization

    @Test
    fun `postCreateOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateOrganization(mockOrganization)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.ClientError<Organization>)
    }

    @Test
    fun `postCreateOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockOrganization)

        val flow = apiImpl.postCreateOrganization(mockOrganization)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.ServerError<Organization>)
    }

    @Test
    fun `postCreateOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockOrganization)

        val flow = apiImpl.postCreateOrganization(mockOrganization)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.Success<Organization>)
    }

    // endregion postCreateOrganization

    // region getAvailableOrganizationBadges

    @Test
    fun `getAvailableOrganizationBadges when client error then Loading+ClientError`() =
        runBlocking {
            mockClientForClientError()

            val flow = apiImpl.getAvailableOrganizationBadges()
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrResource.Loading<Map<String, String>>)
            assert(results[1] is DgfrResource.ClientError<Map<String, String>>)
        }

    @Test
    fun `getAvailableOrganizationBadges when client error then Loading+ServerError`() =
        runBlocking {
            mockClient(HttpStatusCode.BadRequest, emptyMap<String, String>())

            val flow = apiImpl.getAvailableOrganizationBadges()
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrResource.Loading<Map<String, String>>)
            assert(results[1] is DgfrResource.ServerError<Map<String, String>>)
        }

    @Test
    fun `getAvailableOrganizationBadges when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyMap<String, String>())

        val flow = apiImpl.getAvailableOrganizationBadges()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Map<String, String>>)
        assert(results[1] is DgfrResource.Success<Map<String, String>>)
    }

    // endregion getAvailableOrganizationBadges

    // region getOrgRoles

    @Test
    fun `getOrgRoles when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getOrgRoles()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<OrganizationRole>>)
        assert(results[1] is DgfrResource.ClientError<List<OrganizationRole>>)
    }

    @Test
    fun `getOrgRoles when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<OrganizationRole>())

        val flow = apiImpl.getOrgRoles()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<OrganizationRole>>)
        assert(results[1] is DgfrResource.ServerError<List<OrganizationRole>>)
    }

    @Test
    fun `getOrgRoles when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<OrganizationRole>())

        val flow = apiImpl.getOrgRoles()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<OrganizationRole>>)
        assert(results[1] is DgfrResource.Success<List<OrganizationRole>>)
    }

    // endregion getOrgRoles

    // region getSuggestOrganizations

    @Test
    fun `getSuggestOrganizations when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestOrganizations("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<OrganizationSuggestion>>)
        assert(results[1] is DgfrResource.ClientError<List<OrganizationSuggestion>>)
    }

    @Test
    fun `getSuggestOrganizations when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<OrganizationSuggestion>())

        val flow = apiImpl.getSuggestOrganizations("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<OrganizationSuggestion>>)
        assert(results[1] is DgfrResource.ServerError<List<OrganizationSuggestion>>)
    }

    @Test
    fun `getSuggestOrganizations when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<OrganizationSuggestion>())

        val flow = apiImpl.getSuggestOrganizations("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<OrganizationSuggestion>>)
        assert(results[1] is DgfrResource.Success<List<OrganizationSuggestion>>)
    }

    // endregion getSuggestOrganizations

    // region deleteUnfollowOrganization

    @Test
    fun `deleteUnfollowOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnfollowOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteUnfollowOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteUnfollowOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteUnfollowOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteUnfollowOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteUnfollowOrganization

    // region getListOrganizationFollowers

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizationFollowers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListOrganizationFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.ClientError<FollowPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizationFollowers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockFollowPage)

        val flow = apiImpl.getListOrganizationFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.ServerError<FollowPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizationFollowers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockFollowPage)

        val flow = apiImpl.getListOrganizationFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.Success<FollowPage>)
    }

    // endregion getListOrganizationFollowers

    // region postFollowOrganization

    @Test
    fun `postFollowOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postFollowOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `postFollowOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.postFollowOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `postFollowOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.postFollowOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion postFollowOrganization

    // region deleteOrganization

    @Test
    fun `deleteOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteOrganization

    // region getOrganization

    @Test
    fun `getOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.ClientError<Organization>)
    }

    @Test
    fun `getOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockOrganization)

        val flow = apiImpl.getOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.ServerError<Organization>)
    }

    @Test
    fun `getOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockOrganization)

        val flow = apiImpl.getOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.Success<Organization>)
    }

    // endregion getOrganization

    // region putUpdateOrganization

    @Test
    fun `putUpdateOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateOrganization("", mockOrganization)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.ClientError<Organization>)
    }

    @Test
    fun `putUpdateOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockOrganization)

        val flow = apiImpl.putUpdateOrganization("", mockOrganization)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.ServerError<Organization>)
    }

    @Test
    fun `putUpdateOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockOrganization)

        val flow = apiImpl.putUpdateOrganization("", mockOrganization)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Organization>)
        assert(results[1] is DgfrResource.Success<Organization>)
    }

    // endregion putUpdateOrganization

    // region postAddOrganizationBadge

    @Test
    fun `postAddOrganizationBadge when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postAddOrganizationBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Badge>)
        assert(results[1] is DgfrResource.ClientError<Badge>)
    }

    @Test
    fun `postAddOrganizationBadge when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBadge)

        val flow = apiImpl.postAddOrganizationBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Badge>)
        assert(results[1] is DgfrResource.ServerError<Badge>)
    }

    @Test
    fun `postAddOrganizationBadge when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBadge)

        val flow = apiImpl.postAddOrganizationBadge("", mockBadge)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Badge>)
        assert(results[1] is DgfrResource.Success<Badge>)
    }

    // endregion postAddOrganizationBadge

    // region deleteOrganizationBadge

    @Test
    fun `deleteOrganizationBadge when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteOrganizationBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteOrganizationBadge when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteOrganizationBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteOrganizationBadge when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteOrganizationBadge("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteOrganizationBadge

    // region getRdfOrganization

    @Test
    fun `getRdfOrganization when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getRdfOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<String>)
        assert(results[1] is DgfrResource.ClientError<String>)
    }

    @Test
    fun `getRdfOrganization when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockString)

        val flow = apiImpl.getRdfOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<String>)
        assert(results[1] is DgfrResource.ServerError<String>)
    }

    @Test
    fun `getRdfOrganization when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockString)

        val flow = apiImpl.getRdfOrganization("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<String>)
        assert(results[1] is DgfrResource.Success<String>)
    }

    // endregion getRdfOrganization

    // region getRdfOrganizationFormat

    @Test
    fun `getRdfOrganizationFormat when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getRdfOrganizationFormat("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<String>)
        assert(results[1] is DgfrResource.ClientError<String>)
    }

    @Test
    fun `getRdfOrganizationFormat when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockString)

        val flow = apiImpl.getRdfOrganizationFormat("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<String>)
        assert(results[1] is DgfrResource.ServerError<String>)
    }

    @Test
    fun `getRdfOrganizationFormat when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockString)

        val flow = apiImpl.getRdfOrganizationFormat("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<String>)
        assert(results[1] is DgfrResource.Success<String>)
    }

    // endregion getRdfOrganizationFormat

    // region getListOrganizationDatasets

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizationDatasets when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListOrganizationDatasets("", 0, 0, "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<DatasetPage>)
        assert(results[1] is DgfrResource.ClientError<DatasetPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizationDatasets when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockDatasetPage)

        val flow = apiImpl.getListOrganizationDatasets("", 0, 0, "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<DatasetPage>)
        assert(results[1] is DgfrResource.ServerError<DatasetPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListOrganizationDatasets when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockDatasetPage)

        val flow = apiImpl.getListOrganizationDatasets("", 0, 0, "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<DatasetPage>)
        assert(results[1] is DgfrResource.Success<DatasetPage>)
    }

    // endregion getListOrganizationDatasets

    // region getListOrganizationDiscussions

    @Test
    fun `getListOrganizationDiscussions when client error then Loading+ClientError`() =
        runBlocking {
            mockClientForClientError()

            val flow = apiImpl.getListOrganizationDiscussions("")
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrResource.Loading<List<Discussion>>)
            assert(results[1] is DgfrResource.ClientError<List<Discussion>>)
        }

    @Test
    fun `getListOrganizationDiscussions when client error then Loading+ServerError`() =
        runBlocking {
            mockClient(HttpStatusCode.BadRequest, emptyList<Discussion>())

            val flow = apiImpl.getListOrganizationDiscussions("")
            val results = flow.toList()
            Assert.assertEquals(results.size, 2)
            assert(results[0] is DgfrResource.Loading<List<Discussion>>)
            assert(results[1] is DgfrResource.ServerError<List<Discussion>>)
        }

    @Test
    fun `getListOrganizationDiscussions when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Discussion>())

        val flow = apiImpl.getListOrganizationDiscussions("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Discussion>>)
        assert(results[1] is DgfrResource.Success<List<Discussion>>)
    }

    // endregion getListOrganizationDiscussions

    // region getListOrganizationIssues

    @Test
    fun `getListOrganizationIssues when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListOrganizationIssues("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Issue>>)
        assert(results[1] is DgfrResource.ClientError<List<Issue>>)
    }

    @Test
    fun `getListOrganizationIssues when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Issue>())

        val flow = apiImpl.getListOrganizationIssues("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Issue>>)
        assert(results[1] is DgfrResource.ServerError<List<Issue>>)
    }

    @Test
    fun `getListOrganizationIssues when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Issue>())

        val flow = apiImpl.getListOrganizationIssues("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Issue>>)
        assert(results[1] is DgfrResource.Success<List<Issue>>)
    }

    // endregion getListOrganizationIssues

    // region postOrganizationLogo

    @Test
    fun `postOrganizationLogo when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postOrganizationLogo("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `postOrganizationLogo when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.postOrganizationLogo("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `postOrganizationLogo when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.postOrganizationLogo("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion postOrganizationLogo

    // region putResizeOrganizationLogo

    @Test
    fun `putResizeOrganizationLogo when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putResizeOrganizationLogo("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `putResizeOrganizationLogo when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.putResizeOrganizationLogo("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `putResizeOrganizationLogo when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.putResizeOrganizationLogo("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion putResizeOrganizationLogo

    // region deleteOrganizationMember

    @Test
    fun `deleteOrganizationMember when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteOrganizationMember("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteOrganizationMember when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteOrganizationMember("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteOrganizationMember when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteOrganizationMember("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteOrganizationMember

    // region postCreateOrganizationMember

    @Test
    fun `postCreateOrganizationMember when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateOrganizationMember("", "", mockMember)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.ClientError<Member>)
    }

    @Test
    fun `postCreateOrganizationMember when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockMember)

        val flow = apiImpl.postCreateOrganizationMember("", "", mockMember)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.ServerError<Member>)
    }

    @Test
    fun `postCreateOrganizationMember when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockMember)

        val flow = apiImpl.postCreateOrganizationMember("", "", mockMember)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.Success<Member>)
    }

    // endregion postCreateOrganizationMember

    // region putUpdateOrganizationMember

    @Test
    fun `putUpdateOrganizationMember when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateOrganizationMember("", "", mockMember)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.ClientError<Member>)
    }

    @Test
    fun `putUpdateOrganizationMember when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockMember)

        val flow = apiImpl.putUpdateOrganizationMember("", "", mockMember)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.ServerError<Member>)
    }

    @Test
    fun `putUpdateOrganizationMember when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockMember)

        val flow = apiImpl.putUpdateOrganizationMember("", "", mockMember)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.Success<Member>)
    }

    // endregion putUpdateOrganizationMember

    // region getListMembershipRequests

    @Test
    fun `getListMembershipRequests when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListMembershipRequests("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<MembershipRequest>>)
        assert(results[1] is DgfrResource.ClientError<List<MembershipRequest>>)
    }

    @Test
    fun `getListMembershipRequests when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<MembershipRequest>())

        val flow = apiImpl.getListMembershipRequests("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<MembershipRequest>>)
        assert(results[1] is DgfrResource.ServerError<List<MembershipRequest>>)
    }

    @Test
    fun `getListMembershipRequests when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<MembershipRequest>())

        val flow = apiImpl.getListMembershipRequests("", null)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<MembershipRequest>>)
        assert(results[1] is DgfrResource.Success<List<MembershipRequest>>)
    }

    // endregion getListMembershipRequests

    // region postMembershipRequest

    @Test
    fun `postMembershipRequest when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postMembershipRequest("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<MembershipRequest>)
        assert(results[1] is DgfrResource.ClientError<MembershipRequest>)
    }

    @Test
    fun `postMembershipRequest when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockMembershipRequest)

        val flow = apiImpl.postMembershipRequest("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<MembershipRequest>)
        assert(results[1] is DgfrResource.ServerError<MembershipRequest>)
    }

    @Test
    fun `postMembershipRequest when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockMembershipRequest)

        val flow = apiImpl.postMembershipRequest("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<MembershipRequest>)
        assert(results[1] is DgfrResource.Success<MembershipRequest>)
    }

    // endregion postMembershipRequest

    // region postAcceptMembership

    @Test
    fun `postAcceptMembership when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postAcceptMembership("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.ClientError<Member>)
    }

    @Test
    fun `postAcceptMembership when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockMember)

        val flow = apiImpl.postAcceptMembership("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.ServerError<Member>)
    }

    @Test
    fun `postAcceptMembership when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockMember)

        val flow = apiImpl.postAcceptMembership("", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Member>)
        assert(results[1] is DgfrResource.Success<Member>)
    }

    // endregion postAcceptMembership

    // region postRefuseMembership

    @Test
    fun `postRefuseMembership when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postRefuseMembership("", "", mockRefuseMembership)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `postRefuseMembership when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.postRefuseMembership("", "", mockRefuseMembership)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `postRefuseMembership when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.postRefuseMembership("", "", mockRefuseMembership)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion postRefuseMembership

    // region getListOrganizationReuses

    @Test
    fun `getListOrganizationReuses when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListOrganizationReuses("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.ClientError<List<Reuse>>)
    }

    @Test
    fun `getListOrganizationReuses when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<Reuse>())

        val flow = apiImpl.getListOrganizationReuses("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.ServerError<List<Reuse>>)
    }

    @Test
    fun `getListOrganizationReuses when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<Reuse>())

        val flow = apiImpl.getListOrganizationReuses("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<Reuse>>)
        assert(results[1] is DgfrResource.Success<List<Reuse>>)
    }

    // endregion getListOrganizationReuses
}
