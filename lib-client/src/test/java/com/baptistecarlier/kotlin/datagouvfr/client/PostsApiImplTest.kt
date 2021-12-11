package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.api.PostsApiImpl
import com.baptistecarlier.kotlin.datagouvfr.client.mock.*
import com.baptistecarlier.kotlin.datagouvfr.client.mock.ApiMockEngine
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockBoolean
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockPost
import com.baptistecarlier.kotlin.datagouvfr.client.mock.mockPostPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.Post
import com.baptistecarlier.kotlin.datagouvfr.client.model.PostPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.UploadedImage
import io.ktor.http.*
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class PostsApiImplTest {

    private lateinit var apiImpl: PostsApiImpl

    // region Mocks & tools

    private val apiMockEngine = ApiMockEngine()

    private inline fun <reified T> mockClient(httpStatusCode: HttpStatusCode, response: T?) {
        apiImpl = PostsApiImpl(apiMockEngine(httpStatusCode, response))
    }

    private fun mockClientForClientError() {
        apiImpl = PostsApiImpl(apiMockEngine.error())
    }

    // endregion Mocks & tools

    // region getListPosts

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListPosts when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getListPosts()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<PostPage>)
        assert(results[1] is DgfrResource.ClientError<PostPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListPosts when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockPostPage)

        val flow = apiImpl.getListPosts()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<PostPage>)
        assert(results[1] is DgfrResource.ServerError<PostPage>)
    }

    @OptIn(MissingFieldMapping::class)
    @Test
    fun `getListPosts when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockPostPage)

        val flow = apiImpl.getListPosts()
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<PostPage>)
        assert(results[1] is DgfrResource.Success<PostPage>)
    }

    // endregion getListPosts

    // region postCreatePost

    @Test
    fun `postCreatePost when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postCreatePost(mockPost)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ClientError<Post>)
    }

    @Test
    fun `postCreatePost when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockPost)

        val flow = apiImpl.postCreatePost(mockPost)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ServerError<Post>)
    }

    @Test
    fun `postCreatePost when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockPost)

        val flow = apiImpl.postCreatePost(mockPost)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.Success<Post>)
    }

    // endregion postCreatePost

    // region deletePost

    @Test
    fun `deletePost when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deletePost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ClientError<Boolean>)
    }

    @Test
    fun `deletePost when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockBoolean)

        val flow = apiImpl.deletePost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.ServerError<Boolean>)
    }

    @Test
    fun `deletePost when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockBoolean)

        val flow = apiImpl.deletePost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Boolean>)
        assert(results[1] is DgfrResource.Success<Boolean>)
    }

    // endregion deletePost

    // region getPost

    @Test
    fun `getPost when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.getPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ClientError<Post>)
    }

    @Test
    fun `getPost when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockPost)

        val flow = apiImpl.getPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ServerError<Post>)
    }

    @Test
    fun `getPost when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockPost)

        val flow = apiImpl.getPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.Success<Post>)
    }

    // endregion getPost

    // region putUpdatePost

    @Test
    fun `putUpdatePost when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putUpdatePost("", mockPost)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ClientError<Post>)
    }

    @Test
    fun `putUpdatePost when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockPost)

        val flow = apiImpl.putUpdatePost("", mockPost)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ServerError<Post>)
    }

    @Test
    fun `putUpdatePost when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockPost)

        val flow = apiImpl.putUpdatePost("", mockPost)
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.Success<Post>)
    }

    // endregion putUpdatePost

    // region postImage

    @Test
    fun `postImage when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `postImage when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.postImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `postImage when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.postImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion postImage

    // region putResizePostImage

    @Test
    fun `putResizePostImage when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.putResizePostImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ClientError<UploadedImage>)
    }

    @Test
    fun `putResizePostImage when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockUploadedImage)

        val flow = apiImpl.putResizePostImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.ServerError<UploadedImage>)
    }

    @Test
    fun `putResizePostImage when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockUploadedImage)

        val flow = apiImpl.putResizePostImage("", ByteArray(0), "", "")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<UploadedImage>)
        assert(results[1] is DgfrResource.Success<UploadedImage>)
    }

    // endregion putResizePostImage

    // region deleteUnpublishPost

    @Test
    fun `deleteUnpublishPost when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.deleteUnpublishPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ClientError<Post>)
    }

    @Test
    fun `deleteUnpublishPost when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockPost)

        val flow = apiImpl.deleteUnpublishPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ServerError<Post>)
    }

    @Test
    fun `deleteUnpublishPost when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockPost)

        val flow = apiImpl.deleteUnpublishPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.Success<Post>)
    }

    // endregion deleteUnpublishPost

    // region postPublishPost

    @Test
    fun `postPublishPost when client error then Loading+ClientError`() = runBlocking {
        mockClientForClientError()

        val flow = apiImpl.postPublishPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ClientError<Post>)
    }

    @Test
    fun `postPublishPost when client error then Loading+ServerError`() = runBlocking {
        mockClient(HttpStatusCode.BadRequest, mockPost)

        val flow = apiImpl.postPublishPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.ServerError<Post>)
    }

    @Test
    fun `postPublishPost when client error then Loading+Success`() = runBlocking {
        mockClient(HttpStatusCode.OK, mockPost)

        val flow = apiImpl.postPublishPost("")
        val results = flow.toList()
        Assert.assertEquals(results.size, 2)
        assert(results[0] is DgfrResource.Loading<Post>)
        assert(results[1] is DgfrResource.Success<Post>)
    }

    // endregion postPublishPost
}
