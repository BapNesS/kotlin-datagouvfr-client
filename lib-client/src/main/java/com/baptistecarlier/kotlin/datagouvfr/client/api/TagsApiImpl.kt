package com.baptistecarlier.kotlin.datagouvfr.client.api

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrResource
import com.baptistecarlier.kotlin.datagouvfr.client.model.Tag
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow

class TagsApiImpl(private val client: HttpClient) : TagsApi {

    override suspend fun getTagsSuggest(
        q: String,
        size: Int?
    ): Flow<DgfrResource<List<Tag>>> = loadingFlow {
        val builder = StringBuilder()
        builder.appendIfNotNull("q", q)

        client.get(
            path = "tags/suggest/?${builder.urlEncore()}"
        )
    }
}
