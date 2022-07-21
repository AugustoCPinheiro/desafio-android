package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.model.User
import retrofit2.Response

interface ContactsRemoteDataSource {
    suspend fun getUsers(): Response<List<User>>
}