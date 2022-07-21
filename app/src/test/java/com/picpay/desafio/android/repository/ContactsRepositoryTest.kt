package com.picpay.desafio.android.repository

import com.picpay.desafio.android.datasource.ContactsLocalDataSource
import com.picpay.desafio.android.datasource.ContactsRemoteDataSource
import com.picpay.desafio.android.model.User
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.flow.take
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
        val mockUser = User("", "", 0, "")
        val mockUserTwo = User("", "", 1, "")
        val mockSuccessList = listOf(mockUser, mockUserTwo)
        val mockSuccessListTwo = listOf(mockUserTwo)
        val successResponseMock: Response<List<User>> = Response.success(mockSuccessList)
        val errorResponseMock: Response<List<User>> =
            Response.error(404, "".toResponseBody("application/json".toMediaTypeOrNull()))
    }
}