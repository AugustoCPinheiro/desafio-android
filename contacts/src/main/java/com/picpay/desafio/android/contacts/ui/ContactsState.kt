package com.picpay.desafio.android.contacts.ui

import com.picpay.desafio.android.core_entity.User

sealed class ContactsState {
    class Success(val data: List<User>): ContactsState()
    object Loading: ContactsState()
    object Error: ContactsState()
}
