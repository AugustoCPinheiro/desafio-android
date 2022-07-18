package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import retrofit2.Response

interface ContactsRemoteRepository {
    suspend fun getUsers(): Response<List<User>>
}