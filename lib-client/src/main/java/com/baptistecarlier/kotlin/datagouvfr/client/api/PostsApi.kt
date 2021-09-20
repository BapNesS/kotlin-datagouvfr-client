package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.model.Post
import com.baptistecarlier.kotlin.datagouvfr.client.model.PostPage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import io.ktor.client.*
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
    ): Flow<PostPage?>

    /**
     * Create a post
     * @param payload (required)
     */
    suspend fun postCreatePost(payload: Post): Flow<Post?>

    /**
     * Delete a given post
     * @param post The post ID or slug (required)
     */
    suspend fun deletePost(post: String): Flow<Boolean?>

    /**
     * Get a given post
     * @param post The post ID or slug (required)
     */
    suspend fun getPost(post: String): Flow<Post?>

    /**
     * Update a given post
     * @param post The post ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdatePost(post: String, payload: Post): Flow<Post?>

    /**
     * Upload a new image
     * @param post (required)
     * @param file (optional)
     * @param bbox (optional)
     */
    /*suspend fun postImage(
        post: String,
        file: RequestBody? = null,
        bbox: String? = null
    ): Flow<UploadedImage?>*/

    /**
     * Set the image BBox
     * @param post (required)
     * @param file (optional)
     * @param bbox (optional)
     */
    /*suspend fun putResizePostImage(
        post: String,
        file: RequestBody? = null,
        bbox: String? = null
    ): Flow<UploadedImage?>*/

    /**
     * Publish an existing post
     * @param post (required)
     */
    suspend fun deleteUnpublishPost(post: String): Flow<Post?>

    /**
     * Publish an existing post
     * @param post (required)
     */
    suspend fun postPublishPost(post: String): Flow<Post?>

}