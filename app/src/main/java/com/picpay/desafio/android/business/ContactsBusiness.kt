package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRemoteRepository
import com.picpay.desafio.android.repository.db.AppDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.coroutineContext

class ContactsBusiness(
    private val repository: ContactsRemoteRepository,
    private val database: AppDatabase,
    private val dispatcher: CoroutineDispatcher
) {
    suspend fun getUsers(): Flow<List<User>> = flow {
        val result = repository.getUsers()
        if (result.isSuccessful) {
            val users = result.body()!!
            coroutineScope {
                launch(dispatcher) {
                    database.userDao().insertAll(*users.toTypedArray())
                }
            }
            emit(users)
        } else {
            emit(listOf())
        }
    }
}