package com.picpay.desafio.android

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.service.PicPayService
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class ExampleServiceTest {

    private val api = mock<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() = runBlocking{
        // given
        val expectedUsers = emptyList<User>()


        whenever(api.getUsers()).thenReturn(Response.success(expectedUsers))

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}