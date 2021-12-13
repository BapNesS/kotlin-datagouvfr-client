package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Post
import com.baptistecarlier.kotlin.datagouvfr.client.model.PostPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.UploadedImage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.HttpCodeRangeSuccess
import com.baptistecarlier.kotlin.datagouvfr.client.tools.addApiKey
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class PostsApiImpl(private val client: HttpClient) : PostsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListPosts(page: Int?, pageSize: Int?, sort: String?): Flow<DgfrCallState<PostPage>> = loadingFlow {
        client.get(
            path = "posts/"
        ) {
            parameter("sort", sort)
            parameter("page", page)
            parameter("page_size", pageSize)
        }
    }

    override fun postCreatePost(payload: Post): Flow<DgfrCallState<Post>> = loadingFlow {
        client.post(
            path = "posts/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deletePost(post: String): Flow<DgfrCallState<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "posts/$post/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getPost(post: String): Flow<DgfrCallState<Post>> = loadingFlow {
        client.get(
            path = "posts/$post/"
        )
    }

    override fun putUpdatePost(post: String, payload: Post): Flow<DgfrCallState<Post>> = loadingFlow {
        client.put(
            path = "posts/$post/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun postImage(
        post: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "posts/$post/image",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
            }
        ) {
            method = HttpMethod.Post
            header("X-API-KEY", apiKey)
        }
    }

    override fun putResizePostImage(
        post: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrCallState<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "posts/$post/image",
            formData = formData {
                append(
                    "file", file,
                    Headers.build {
                        append(HttpHeaders.ContentDisposition, "filename=$fileName")
                        append(HttpHeaders.ContentType, contentType)
                    }
                )
            }
        ) {
            method = HttpMethod.Put
            header("X-API-KEY", apiKey)
        }
    }

    override fun deleteUnpublishPost(post: String): Flow<DgfrCallState<Post>> = loadingFlow {
        client.delete(
            path = "posts/$post/publish"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postPublishPost(post: String): Flow<DgfrCallState<Post>> = loadingFlow {
        client.post(
            path = "posts/$post/publish"
        ) {
            addApiKey(apiKey)
        }
    }
}
