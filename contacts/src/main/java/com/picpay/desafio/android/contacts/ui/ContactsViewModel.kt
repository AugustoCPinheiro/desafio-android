package com.picpay.desafio.android.contacts.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.contacts.business.GetContactsUseCase
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContactsViewModel(
    private val getContactsUseCase: GetContactsUseCase,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Main
) : ViewModel() {
    private val _contactsState: MutableLiveData<ContactsState> by lazy {
        MutableLiveData<ContactsState>()
    }

    val contactsState: LiveData<ContactsState> = _contactsState

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch(defaultDispatcher) {
            getContactsUseCase()
                .onStart { _contactsState.value = ContactsState.Loading }
                .collect {
                    if (it.isNotEmpty()) {
                        _contactsState.value = ContactsState.Success(it)
                    } else {
                        _contactsState.value = ContactsState.Error
                    }
                }
        }
    }
}