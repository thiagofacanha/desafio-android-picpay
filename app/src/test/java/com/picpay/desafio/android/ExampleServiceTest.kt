package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.data.remote.service.PicPayService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Test
import retrofit2.Call
import retrofit2.Response

@OptIn(ExperimentalCoroutinesApi::class)
class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() = runTest {
        // given
        val expectedUsers = emptyList<RemoteUser>()

        whenever(api.getUsers()).thenReturn(emptyList())

        // when
        val users = service.example()

        // then
        assertEquals(true, true)
    }
}