package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Post
import com.baptistecarlier.kotlin.datagouvfr.client.model.PostPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.UploadedImage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.*
import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.client.request.forms.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.coroutines.flow.Flow

internal class PostsApiImpl(private val client: HttpClient): PostsApi {

    private var apiKey: String = ""
    override fun setApiKey(apiKey: String) {
        this.apiKey = apiKey
    }

    @OptIn(MissingFieldMapping::class)
    override fun getListPosts(page: Int?, pageSize: Int?, sort: String?): Flow<DgfrResource<PostPage>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("sort", sort)
        builder.appendIfNotNull("page", page)
        builder.appendIfNotNull("page_size", pageSize)

        client.get(
            path = "posts/?${builder.urlEncore()}"
        )
    }

    override fun postCreatePost(payload: Post): Flow<DgfrResource<Post>> = loadingFlow {
        client.post(
            path = "posts/"
        ) {
            addApiKey(apiKey)
            contentType(ContentType.Application.Json)
            body = payload
        }
    }

    override fun deletePost(post: String): Flow<DgfrResource<Boolean>> = loadingFlow {
        val response = client.delete<HttpResponse>(
            path = "posts/$post/"
        ) {
            addApiKey(apiKey)
        }
        response.status.value in HttpCodeRangeSuccess
    }

    override fun getPost(post: String): Flow<DgfrResource<Post>> = loadingFlow {
        client.get(
            path = "posts/$post/"
        )
    }

    override fun putUpdatePost(post: String, payload: Post): Flow<DgfrResource<Post>> = loadingFlow {
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
    ): Flow<DgfrResource<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "posts/$post/image",
            formData = formData {
                append("file", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    append(HttpHeaders.ContentType, contentType)
                })
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
    ): Flow<DgfrResource<UploadedImage>> = loadingFlow {
        client.submitFormWithBinaryData(
            url = "posts/$post/image",
            formData = formData {
                append("file", file, Headers.build {
                    append(HttpHeaders.ContentDisposition, "filename=$fileName")
                    append(HttpHeaders.ContentType, contentType)
                })
            }
        ) {
            method = HttpMethod.Put
            header("X-API-KEY", apiKey)
        }
    }

    override fun deleteUnpublishPost(post: String): Flow<DgfrResource<Post>> = loadingFlow {
        client.delete(
            path = "posts/$post/publish/"
        ) {
            addApiKey(apiKey)
        }
    }

    override fun postPublishPost(post: String): Flow<DgfrResource<Post>> = loadingFlow {
        client.post(
            path = "posts/$post/publish/"
        ) {
            addApiKey(apiKey)
        }
    }
}