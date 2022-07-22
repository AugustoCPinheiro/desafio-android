package com.picpay.desafio.android.contacts.datasource

import com.picpay.desafio.android.core_entity.User

interface ContactsLocalDataSource {
    fun saveContacts(contacts: List<User>)
    fun getContacts(): List<User>
}