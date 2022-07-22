package com.picpay.desafio.android.contacts.repository

import com.picpay.desafio.android.core_entity.User
import kotlinx.coroutines.flow.Flow

interface ContactsRepository {
    fun getUsers(): Flow<List<User>>
}