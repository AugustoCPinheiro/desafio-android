package com.picpay.desafio.android.repository

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.service.PicPayService
import retrofit2.Response

class ContactsRemoteRepositoryImpl(private val service: PicPayService) : ContactsRemoteRepository {
    override suspend fun getUsers(): Response<List<User>> = service.getUsers()
}