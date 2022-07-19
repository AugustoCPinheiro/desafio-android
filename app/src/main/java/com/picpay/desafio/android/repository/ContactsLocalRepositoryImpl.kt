package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.db.UserDao

class ContactsLocalRepositoryImpl(private val userDao: UserDao) : ContactsLocalRepository {
    override fun saveContacts(contacts: List<User>) {
        userDao.insertAll(*contacts.toTypedArray())
    }

    override fun getContacts(): List<User> {
        return userDao.getAll()
    }
}