package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRemoteRepository
import com.picpay.desafio.android.repository.db.UserDao
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
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
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class GetContactsUseCaseTest {

    private val mockUserDao = mockk<UserDao>()
    private val mockRepository = mockk<ContactsRemoteRepository>()

    private lateinit var useCase: GetContactsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        useCase = GetContactsUseCaseImpl(mockRepository, mockUserDao, testDispatcher)
    }

    @Test
    fun shouldNotEmitCache_whenDatabaseIsEmpty() = runTest(testDispatcher) {
        every { mockUserDao.getAll() } returns listOf()
        coEvery { mockRepository.getUsers() } returns successResponseMock
        every { mockUserDao.insertAll(*anyVararg()) } answers { nothing }

        val caseFlow = useCase()

        runCurrent()

        caseFlow.collect {
            assertEquals(mockSuccessList, it)
        }

    }

    @Test
    fun shouldEmitCache_thenRemoteAnswer_whenDatabaseIsPopulated() = runTest(testDispatcher) {
        every { mockUserDao.getAll() } returns mockSuccessListTwo
        coEvery { mockRepository.getUsers() } returns successResponseMock
        every { mockUserDao.insertAll(*anyVararg()) } answers { nothing }

        val caseFlow = useCase()

        runCurrent()

        caseFlow.take(1).collect {
            assertEquals(mockSuccessListTwo, it)
        }
        caseFlow.drop(1).collect {
            assertEquals(mockSuccessList, it)
        }
    }


    @Test
    fun shouldEmitEmptyList_whenRemoteAnswer_isNotSuccessful() = runTest(testDispatcher) {
        every { mockUserDao.getAll() } returns listOf()
        coEvery { mockRepository.getUsers() } returns errorResponseMock
        every { mockUserDao.insertAll(*anyVararg()) } answers { nothing }

        val caseFlow = useCase()
        runCurrent()

        caseFlow.collect {
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