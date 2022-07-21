package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import kotlinx.coroutines.flow.Flow
import retrofit2.Response

interface ContactsRepository {
    fun getUsers(): Flow<List<User>>
}