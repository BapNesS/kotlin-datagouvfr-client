package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Topic
import com.baptistecarlier.kotlin.datagouvfr.client.model.TopicPage
import com.baptistecarlier.kotlin.datagouvfr.client.tools.WithApiKey
import kotlinx.coroutines.flow.Flow

/**
 * Topics related operations
 */
interface TopicsApi: WithApiKey {

    /**
     * List all topics
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    suspend fun getListTopics(page: Int? = null, pageSize: Int? = null): Flow<DgfrResource<TopicPage>>

    /**
     * Create a topic
     * @param payload (required)
     */
    suspend fun postCreateTopic(payload: Topic): Flow<DgfrResource<Topic>>

    /**
     * Delete a given topic
     * @param topic The topic ID or slug (required)
     */
    suspend fun deleteTopic(topic: String): Flow<DgfrResource<Boolean>>

    /**
     * Get a given topic
     * @param topic The topic ID or slug (required)
     */
    suspend fun getTopic(topic: String): Flow<DgfrResource<Topic>>

    /**
     * Update a given topic
     * @param topic The topic ID or slug (required)
     * @param payload (required)
     */
    suspend fun putUpdateTopic(topic: String, payload: Topic): Flow<DgfrResource<Topic>>

}