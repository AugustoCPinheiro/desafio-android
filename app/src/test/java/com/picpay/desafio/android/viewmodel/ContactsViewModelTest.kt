package com.picpay.desafio.android.viewmodel

import com.picpay.desafio.android.business.GetContactsUseCase
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import io.mockk.mockk
import io.mockk.verify
import kotlinx.coroutines.delay
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test

class ContactsViewModelTest {

    private val mockGetContactsUseCase = mockk<GetContactsUseCase>(relaxed = true)

    private lateinit var viewModel: ContactsViewModel

    @Before
    fun setup() {
        viewModel = ContactsViewModel(mockGetContactsUseCase, StandardTestDispatcher())
    }

    @Test
    fun whenInitialized_ShouldCallUseCase() = runTest {
        delay(1000)
        verify(exactly = 1) {

            mockGetContactsUseCase()
        }
    }
}