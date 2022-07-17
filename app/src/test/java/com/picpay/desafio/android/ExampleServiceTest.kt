package com.picpay.desafio.android

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.service.PicPayService
import io.mockk.coEvery
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test
import retrofit2.Response

class ExampleServiceTest {

    private val api = mockk<PicPayService>()

    private val service = ExampleService(api)

    @Test
    fun exampleTest() = runBlocking{
        // given
        val expectedUsers = emptyList<User>()


        coEvery {  api.getUsers() } returns Response.success(expectedUsers)

        // when
        val users = service.example()

        // then
        assertEquals(users, expectedUsers)
    }
}