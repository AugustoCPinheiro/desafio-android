package com.picpay.desafio.android.ui.contacts

import com.picpay.desafio.android.model.User

sealed class ContactsState {
    class Success(val data: List<User>): ContactsState()
    object Loading: ContactsState()
    object Error: ContactsState()
}
