package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.model.User

interface ContactsLocalDataSource {
    fun saveContacts(contacts: List<User>)
    fun getContacts(): List<User>
}