package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.datasource.service.PicPayService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

class ContactsRemoteRepositoryTest {

    private val mockService = mockk<PicPayService>()

    private lateinit var repository: ContactsRepository

    @Before
    fun setup() {
        repository = ContactsRepositoryImpl(mockService)
    }

    @Test
    fun whenServiceReturnsSuccess_shouldPassOn() = runBlocking {
        coEvery { mockService.getUsers() } returns mockSuccessResponse

        val result = repository.getUsers()

        assertEquals(mockSuccessResponse, result)
    }

    @Test
    fun whenServiceReturnsError_shouldPassOn() = runBlocking {
        coEvery { mockService.getUsers() } returns mockErrorResponse

        val result = repository.getUsers()

        assertEquals(mockErrorResponse, result)
    }

    private companion object {
        val mockUser = User("", "", 0, "")
        val mockSuccessList = listOf(mockUser)
        val mockSuccessResponse: Response<List<User>> = Response.success(mockSuccessList)
        val mockErrorResponse: Response<List<User>> =
            Response.error(404, "".toResponseBody("application/json".toMediaTypeOrNull()))
    }
}