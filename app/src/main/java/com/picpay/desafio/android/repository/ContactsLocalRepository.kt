package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User

interface ContactsLocalRepository {
    fun saveContacts(contacts: List<User>)
    fun getContacts(): List<User>
}