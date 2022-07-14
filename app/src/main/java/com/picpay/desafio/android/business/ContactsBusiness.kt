package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class ContactsBusiness(private val repository: ContactsRepository) {
    suspend fun getUsers(): Flow<List<User>> = flow {
        val result = repository.getUsers()
        if(result.isSuccessful){
            emit(result.body()!!)
        }else{
            emit(listOf())
        }
    }
}