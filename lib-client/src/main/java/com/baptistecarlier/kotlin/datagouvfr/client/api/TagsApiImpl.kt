package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.model.Tag
import com.baptistecarlier.kotlin.datagouvfr.client.tools.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.client.tools.urlEncore
import io.ktor.client.*
import io.ktor.client.request.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class TagsApiImpl(private val client: HttpClient) : TagsApi {

    private val tag = "TagsApiImpl"

    override suspend fun getTagsSuggest(
        q: String,
        size: Int?
    ): Flow<List<Tag>?> = flow {
        Log.d(tag, "getSuggest / begin")
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)

            val response = client.get<List<Tag>>(
                path = "tags/suggest/?${builder.urlEncore()}"
            )
            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggest / Exception =  $e")
            null
        }
        emit(value)
    }
}
