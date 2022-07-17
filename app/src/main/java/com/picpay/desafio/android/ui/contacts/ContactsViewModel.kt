package com.picpay.desafio.android.ui.contacts

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.business.GetContactsUseCase
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.launch

class ContactsViewModel(private val getContactsUseCase: GetContactsUseCase) : ViewModel() {
    private val _contactsState: MutableLiveData<ContactsState> by lazy {
        MutableLiveData<ContactsState>()
    }

    val contactsState: LiveData<ContactsState> = _contactsState

    init {
        getUsers()
    }

    private fun getUsers() {
        viewModelScope.launch {
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