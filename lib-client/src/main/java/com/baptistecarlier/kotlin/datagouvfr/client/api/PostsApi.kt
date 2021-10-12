package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Post
import com.baptistecarlier.kotlin.datagouvfr.client.model.PostPage
import com.baptistecarlier.kotlin.datagouvfr.client.model.UploadedImage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Posts related operations
 */
interface PostsApi : WithApiKey {

    /**
     * List all posts
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     * @param sort The sorting attribute (optional, defa
     */
    suspend fun getListPosts(
        page: Int? = null,
        pageSize: Int? = null,
        sort: String? = null
    ): Flow<DgfrResource<PostPage>>

    /**
     * Create a post
     * @param payload (required)
     */
    suspend fun postCreatePost(payload: Post): Flow<DgfrResource<Post>>

    /**
     * Delete a given post
     * @param post The post ID or slug (required)
     */
    suspend fun deletePost(post: String): Flow<DgfrResource<Boolean>>

    /**
     * Get a given post
     * @param post The post ID or slug (required)
     */
    suspend fun getPost(post: String): Flow<DgfrResource<Post>>

    /**
     * Update a given post
     * @param post The post ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdatePost(post: String, payload: Post): Flow<DgfrResource<Post>>

    /**
     * Upload a new image
     * @param post (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    suspend fun postImage(
        post: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>>

    /**
     * Set the image BBox
     * @param post (required)
     * @param file content byte array (required)
     * @param fileName file name with extension (required)
     * @param contentType content type (required)
     */
    suspend fun putResizePostImage(
        post: String,
        file: ByteArray,
        fileName: String,
        contentType: String
    ): Flow<DgfrResource<UploadedImage>>

    /**
     * Publish an existing post
     * @param post (required)
     */
    suspend fun deleteUnpublishPost(post: String): Flow<DgfrResource<Post>>

    /**
     * Publish an existing post
     * @param post (required)
     */
    suspend fun postPublishPost(post: String): Flow<DgfrResource<Post>>

}