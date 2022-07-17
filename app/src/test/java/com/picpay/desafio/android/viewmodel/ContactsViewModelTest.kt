package com.picpay.desafio.android.viewmodel

import com.nhaarman.mockitokotlin2.mock
import com.picpay.desafio.android.business.ContactsBusiness
import com.picpay.desafio.android.ui.contacts.ContactsState
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ContactsViewModelTest {

    private val mockContactsBusiness = mock<ContactsBusiness>()
    private lateinit var viewModel: ContactsViewModel

    @Before
    fun setup() {
        viewModel = ContactsViewModel(mockContactsBusiness)
    }

    @Test
    fun whenInitialized_shouldHaveInitState() {
        assertEquals(ContactsState.Loading, viewModel.contactsState.value)
    }
}