package com.baptistecarlier.kotlin.datagouvfr.client.api

import android.util.Log
import com.baptistecarlier.kotlin.datagouvfr.client.models.Tag
import com.baptistecarlier.kotlin.datagouvfr.util.appendIfNotNull
import com.baptistecarlier.kotlin.datagouvfr.util.urlEncore
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
        val value = try {
            val builder = StringBuilder()
            builder.appendIfNotNull("q", q)
            Log.d(tag, "getSuggest / begin")
            val response = client.get<List<Tag>>(
                path = "tags/suggest/?${builder.urlEncore()}"
            )
            Log.d(tag, "getSuggest / response = $response")

            response
        } catch (e: Exception) {
            Log.d(tag, "getSuggest / Crash = $e")
            e.printStackTrace()
            null
        }
        emit(value)
    }
}