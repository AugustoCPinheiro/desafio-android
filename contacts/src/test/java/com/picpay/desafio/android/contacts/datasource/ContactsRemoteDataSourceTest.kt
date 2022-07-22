package com.picpay.desafio.android.contacts.datasource

import com.picpay.desafio.android.core_network.service.PicPayService
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import kotlin.test.assertEquals

class ContactsRemoteDataSourceTest {
    private val mockService = mockk<PicPayService>()

    private lateinit var mockDataSource: ContactsRemoteDataSource

    @Before
    fun setup() {
        mockDataSource = ContactsRemoteDataSourceImpl(mockService)
    }

    @Test
    fun whenServiceReturnsSuccess_shouldPassOn() = runBlocking {
        coEvery { mockService.getUsers() } returns mockSuccessResponse

        val result = mockDataSource.getUsers()

        assertEquals(mockSuccessResponse, result)
    }

    @Test
    fun whenServiceReturnsError_shouldPassOn() = runBlocking {
        coEvery { mockService.getUsers() } returns mockErrorResponse

        val result = mockDataSource.getUsers()

        assertEquals(mockErrorResponse, result)
    }

    private companion object {
        val mockUser = com.picpay.desafio.android.core_entity.User("", "", 0, "")
        val mockSuccessList = listOf(mockUser)
        val mockSuccessResponse: Response<List<com.picpay.desafio.android.core_entity.User>> = Response.success(
            mockSuccessList
        )
        val mockErrorResponse: Response<List<com.picpay.desafio.android.core_entity.User>> =
            Response.error(404, "".toResponseBody("application/json".toMediaTypeOrNull()))
    }
}