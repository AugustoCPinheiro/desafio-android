package com.picpay.desafio.android.contacts.repository

import com.picpay.desafio.android.contacts.datasource.ContactsLocalDataSource
import com.picpay.desafio.android.contacts.datasource.ContactsRemoteDataSource
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.ResponseBody.Companion.toResponseBody
import org.junit.Before
import org.junit.Test
import retrofit2.Response
import java.io.IOException
import kotlin.test.assertTrue

class ContactsRepositoryTest {

    private val mockLocalDataSource = mockk<ContactsLocalDataSource>()
    private val mockRemoteDataSource = mockk<ContactsRemoteDataSource>()

    private lateinit var mockRepository: ContactsRepository

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        mockRepository =
            ContactsRepositoryImpl(mockRemoteDataSource, mockLocalDataSource, testDispatcher)
    }

    @Test
    fun shouldNotEmitCache_whenDatabaseIsEmpty() = runTest(testDispatcher) {
        every { mockLocalDataSource.getContacts() } returns listOf()
        every { mockLocalDataSource.saveContacts(any()) } answers { nothing }
        coEvery { mockRemoteDataSource.getUsers() } returns successResponseMock


        val repositoryFlow = mockRepository.getUsers()

        runCurrent()

        repositoryFlow.collect {
            assertEquals(mockSuccessList, it)
        }

    }

    @Test
    fun shouldEmitCache_whenNoConnection() = runTest(testDispatcher) {
        every { mockLocalDataSource.getContacts() } returns mockSuccessListTwo
        every { mockLocalDataSource.saveContacts(any()) } answers { nothing }
        coEvery { mockRemoteDataSource.getUsers() } throws IOException()

        val repositoryFlow = mockRepository.getUsers()

        runCurrent()

        repositoryFlow.collect {
            assertEquals(mockSuccessListTwo, it)
        }
    }


    @Test
    fun shouldEmitEmptyList_whenRemoteAnswer_isNotSuccessful() = runTest(testDispatcher) {
        every { mockLocalDataSource.getContacts() } returns listOf()
        every { mockLocalDataSource.saveContacts(any()) } answers { nothing }
        coEvery { mockRemoteDataSource.getUsers() } returns errorResponseMock


        val repositoryFlow = mockRepository.getUsers()

        runCurrent()

        repositoryFlow.collect {
            assertTrue { it.isEmpty() }
        }
    }

    private companion object {
        val mockUser = com.picpay.desafio.android.core_entity.User("", "", 0, "")
        val mockUserTwo = com.picpay.desafio.android.core_entity.User("", "", 1, "")
        val mockSuccessList = listOf(mockUser, mockUserTwo)
        val mockSuccessListTwo = listOf(mockUserTwo)
        val successResponseMock: Response<List<com.picpay.desafio.android.core_entity.User>> = Response.success(
            mockSuccessList
        )
        val errorResponseMock: Response<List<com.picpay.desafio.android.core_entity.User>> =
            Response.error(404, "".toResponseBody("application/json".toMediaTypeOrNull()))
    }
}