package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRepository
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runCurrent
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class GetContactsUseCaseTest {


    private val mockRepository = mockk<ContactsRepository>()

    private lateinit var useCase: GetContactsUseCase

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        useCase = GetContactsUseCase(mockRepository)
    }

    @Test
    fun shouldNotEmitCache_whenDatabaseIsEmpty() = runTest(testDispatcher) {
        coEvery { mockRepository.getUsers() } returns flow {
            emit(mockSuccessList)
        }

        val caseFlow = useCase()

        runCurrent()

        caseFlow.collect {
            assertEquals(mockSuccessList, it)
        }

    }

    private companion object {
        val mockUser = User("", "", 0, "")
        val mockSuccessList = listOf(mockUser)
    }
}