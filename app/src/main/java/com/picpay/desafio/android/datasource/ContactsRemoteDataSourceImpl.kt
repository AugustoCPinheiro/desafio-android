package com.picpay.desafio.android.datasource

import com.picpay.desafio.android.datasource.service.PicPayService
import com.picpay.desafio.android.model.User
import retrofit2.Response

class ContactsRemoteDataSourceImpl(private val service: PicPayService) : ContactsRemoteDataSource {
    override suspend fun getUsers(): Response<List<User>> = service.getUsers()
}