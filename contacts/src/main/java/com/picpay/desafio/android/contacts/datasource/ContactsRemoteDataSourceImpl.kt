package com.picpay.desafio.android.contacts.datasource

import com.picpay.desafio.android.core_entity.User
import com.picpay.desafio.android.core_network.service.PicPayService
import retrofit2.Response

class ContactsRemoteDataSourceImpl(private val service: PicPayService) : ContactsRemoteDataSource {
    override suspend fun getUsers(): Response<List<User>> = service.getUsers()
}