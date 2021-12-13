package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.DgfrCallState
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.model.Tag
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

internal class TagsApiImpl(private val client: HttpClient) : TagsApi {

    override fun getTagsSuggest(
        q: String,
        size: Int?
    ): Flow<DgfrCallState<List<Tag>>> = loadingFlow {
        client.get(
            path = "tags/suggest/"
        ) {
            parameter("q", q)
        }
    }
}
