package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.UsersApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.model.*
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class UsersApiImplTest {

    private lateinit var apiImpl: UsersApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = UsersApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = UsersApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListUsers

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListUsers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListUsers("", "", "", "", "", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UserPage>)
        assert(results[1] is DgfrResource.ClientError<UserPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListUsers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUserPage)

        val flow = apiImpl.getListUsers("", "", "", "", "", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UserPage>)
        assert(results[1] is DgfrResource.ServerError<UserPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListUsers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUserPage)

        val flow = apiImpl.getListUsers("", "", "", "", "", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UserPage>)
        assert(results[1] is DgfrResource.Success<UserPage>)
    }

    // endregion getListUsers

    // region postCreateUser

    @Test
    fun `postCreateUser when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreateUser(mockUser)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.ClientError<User>)
    }

    @Test
    fun `postCreateUser when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUser)

        val flow = apiImpl.postCreateUser(mockUser)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.ServerError<User>)
    }

    @Test
    fun `postCreateUser when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUser)

        val flow = apiImpl.postCreateUser(mockUser)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.Success<User>)
    }

    // endregion postCreateUser

    // region getUserRoles

    @Test
    fun `getUserRoles when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getUserRoles()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<UserRole>>)
        assert(results[1] is DgfrResource.ClientError<List<UserRole>>)
    }

    @Test
    fun `getUserRoles when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<UserRole>())

        val flow = apiImpl.getUserRoles()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<UserRole>>)
        assert(results[1] is DgfrResource.ServerError<List<UserRole>>)
    }

    @Test
    fun `getUserRoles when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<UserRole>())

        val flow = apiImpl.getUserRoles()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<UserRole>>)
        assert(results[1] is DgfrResource.Success<List<UserRole>>)
    }

    // endregion getUserRoles

    // region getSuggestUsers

    @Test
    fun `getSuggestUsers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getSuggestUsers("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<UserSuggestion>>)
        assert(results[1] is DgfrResource.ClientError<List<UserSuggestion>>)
    }

    @Test
    fun `getSuggestUsers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, emptyList<UserSuggestion>())

        val flow = apiImpl.getSuggestUsers("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<UserSuggestion>>)
        assert(results[1] is DgfrResource.ServerError<List<UserSuggestion>>)
    }

    @Test
    fun `getSuggestUsers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, emptyList<UserSuggestion>())

        val flow = apiImpl.getSuggestUsers("", 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<List<UserSuggestion>>)
        assert(results[1] is DgfrResource.Success<List<UserSuggestion>>)
    }

    // endregion getSuggestUsers

    // region deleteUnfollowUser

    @Test
    fun `deleteUnfollowUser when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnfollowUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteUnfollowUser when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteUnfollowUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteUnfollowUser when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteUnfollowUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteUnfollowUser

    // region getListUserFollowers

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListUserFollowers when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListUserFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.ClientError<FollowPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListUserFollowers when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockFollowPage)

        val flow = apiImpl.getListUserFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.ServerError<FollowPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListUserFollowers when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockFollowPage)

        val flow = apiImpl.getListUserFollowers("", 0, 0)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<FollowPage>)
        assert(results[1] is DgfrResource.Success<FollowPage>)
    }

    // endregion getListUserFollowers

    // region postFollowUser

    @Test
    fun `postFollowUser when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postFollowUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `postFollowUser when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.postFollowUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `postFollowUser when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.postFollowUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion postFollowUser

    // region deleteUser

    @Test
    fun `deleteUser when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deleteUser when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deleteUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deleteUser when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deleteUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deleteUser

    // region getUser

    @Test
    fun `getUser when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.ClientError<User>)
    }

    @Test
    fun `getUser when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUser)

        val flow = apiImpl.getUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.ServerError<User>)
    }

    @Test
    fun `getUser when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUser)

        val flow = apiImpl.getUser("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.Success<User>)
    }

    // endregion getUser

    // region putUpdateUser

    @Test
    fun `putUpdateUser when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdateUser("", mockUser)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.ClientError<User>)
    }

    @Test
    fun `putUpdateUser when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUser)

        val flow = apiImpl.putUpdateUser("", mockUser)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.ServerError<User>)
    }

    @Test
    fun `putUpdateUser when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUser)

        val flow = apiImpl.putUpdateUser("", mockUser)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<User>)
        assert(results[1] is DgfrResource.Success<User>)
    }

    // endregion putUpdateUser

    // region postUserAvatar

    @Test
    fun `postUserAvatar when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postUserAvatar("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `postUserAvatar when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.postUserAvatar("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `postUserAvatar when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.postUserAvatar("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion postUserAvatar
}
