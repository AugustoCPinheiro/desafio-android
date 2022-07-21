package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.datasource.db.UserDao
import com.picpay.desafio.android.model.User

class ContactsLocalDataSourceImpl(private val userDao: UserDao) : ContactsLocalDataSource {
    override fun saveContacts(contacts: List<User>) {
        userDao.insertAll(*contacts.toTypedArray())
    }

    override fun getContacts(): List<User> {
        return userDao.getAll()
    }
}