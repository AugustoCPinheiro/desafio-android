package com.picpay.desafio.android.contacts.datasource

import com.picpay.desafio.android.core_database.converter.convertToPersistenceUser
import com.picpay.desafio.android.core_database.converter.convertToUser
import com.picpay.desafio.android.core_entity.User

class ContactsLocalDataSourceImpl(private val userDao: com.picpay.desafio.android.core_database.db.UserDao) :
    ContactsLocalDataSource {
    override fun saveContacts(contacts: List<User>) {
        userDao.insertAll(*contacts.map { it.convertToPersistenceUser() }.toTypedArray())
    }

    override fun getContacts(): List<User> {
        return userDao.getAll().map { it.convertToUser() }
    }
}