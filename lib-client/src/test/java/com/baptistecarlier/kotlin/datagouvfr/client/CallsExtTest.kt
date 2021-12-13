package com.baptistecarlier.kotlin.datagouvfr.client

import com.baptistecarlier.kotlin.datagouvfr.client.exception.DgfrException
import com.baptistecarlier.kotlin.datagouvfr.client.exception.exceptionalHandledCall
import com.baptistecarlier.kotlin.datagouvfr.client.exception.loadingFlow
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

internal class CallsExtTest {

    @Test
    fun `loadingFlow then at least one Loading`() = runBlocking {
        val flow = loadingFlow { }

        val result = flow.toList()
        Assert.assertTrue(result.isNotEmpty())
        assert(result[0] is DgfrCallState.Loading<*>)
    }

    @Test
    fun `exceptionalHandledCall when ok then Success`() = runBlocking {
        val result = exceptionalHandledCall {
            true
        }

        assert(result is DgfrCallState.Success<*>)
    }

    @Test
    fun `exceptionalHandledCall when DgfrException then ServerError`() = runBlocking {
        val result = exceptionalHandledCall {
            throw DgfrException(0)
            true
        }

        assert(result is DgfrCallState.ServerError<*>)
    }

    @Test
    fun `exceptionalHandledCall when other Exception then ClientError`() = runBlocking {
        val result = exceptionalHandledCall {
            throw IllegalStateException()
            true
        }

        assert(result is DgfrCallState.ClientError<*>)
    }
}
