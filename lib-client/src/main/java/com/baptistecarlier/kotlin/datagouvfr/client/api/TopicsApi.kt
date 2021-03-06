package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.annotation.MissingFieldMapping
import com.baptistecarlier.kotlin.datagouvfr.client.model.Topic
import com.baptistecarlier.kotlin.datagouvfr.client.model.TopicPage
import kotlinx.coroutines.flow.Flow

/**
 * Topics related operations
 */
interface TopicsApi : WithApiKey {

    /**
     * List all topics
     * @param page The page to fetch (optional, default to 1)
     * @param pageSize The page size to fetch (optional, default to 20)
     */
    @OptIn(MissingFieldMapping::class)
    fun getListTopics(page: Int? = null, pageSize: Int? = null): Flow<DgfrCallState<TopicPage>>

    /**
     * Create a topic
     * @param payload (required)
     */
    fun postCreateTopic(payload: Topic): Flow<DgfrCallState<Topic>>

    /**
     * Delete a given topic
     * @param topic The topic ID or slug (required)
     */
    fun deleteTopic(topic: String): Flow<DgfrCallState<Boolean>>

    /**
     * Get a given topic
     * @param topic The topic ID or slug (required)
     */
    fun getTopic(topic: String): Flow<DgfrCallState<Topic>>

    /**
     * Update a given topic
     * @param topic The topic ID or slug (required)
     * @param payload (required)
     */
    fun putUpdateTopic(topic: String, payload: Topic): Flow<DgfrCallState<Topic>>
}
