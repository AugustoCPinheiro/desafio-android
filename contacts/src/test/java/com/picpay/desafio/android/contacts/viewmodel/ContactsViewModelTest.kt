package com.picpay.desafio.android.contacts.viewmodel

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.picpay.desafio.android.contacts.business.GetContactsUseCase
import com.picpay.desafio.android.contacts.ui.ContactsState
import com.picpay.desafio.android.contacts.ui.ContactsViewModel
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.runCurrent
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class ContactsViewModelTest {

    @get:Rule
    var rule = InstantTaskExecutorRule()

    private val mockGetContactsUseCase = mockk<GetContactsUseCase>(relaxed = true)

    private lateinit var viewModel: ContactsViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setup() {
        viewModel = ContactsViewModel(mockGetContactsUseCase, testDispatcher)
    }

    @Test
    fun whenInitialized_ShouldCallUseCase() = runTest(testDispatcher) {

        runCurrent()

        verify(exactly = 1) {
            mockGetContactsUseCase()
        }
    }

    @Test
    fun whenGetContacts_sendLoadingState() = runTest(testDispatcher) {
        runCurrent()
        assertTrue(viewModel.contactsState.value is ContactsState.Loading)
    }

    @Test
    fun whenContactsList_isEmpty_sendErrorState() = runTest(testDispatcher) {
        every { mockGetContactsUseCase() } returns flow {
            emit(listOf())
        }
        runCurrent()
        assertTrue(viewModel.contactsState.value is ContactsState.Error)
    }

    @Test
    fun whenContactsList_isNotEmpty_sendSuccessState() = runTest(testDispatcher) {
        every { mockGetContactsUseCase() } returns flow {
            emit(mockSuccessList)
        }
        runCurrent()
        assertTrue(viewModel.contactsState.value is ContactsState.Success)
    }

    @Test
    fun whenSuccessState_sentData_isEqualToRequested() = runTest(testDispatcher) {
        every { mockGetContactsUseCase() } returns flow {
            emit(mockSuccessList)
        }
        runCurrent()
        assertEquals((viewModel.contactsState.value as ContactsState.Success).data, mockSuccessList)
    }

    private companion object {
        val mockUser = com.picpay.desafio.android.core_entity.User("", "", 0, "")
        val mockSuccessList = listOf(mockUser)
    }


}