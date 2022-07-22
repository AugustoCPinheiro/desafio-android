package com.picpay.desafio.android.contacts.datasource

import com.picpay.desafio.android.core_entity.User
import retrofit2.Response

interface ContactsRemoteDataSource {
    suspend fun getUsers(): Response<List<User>>
}