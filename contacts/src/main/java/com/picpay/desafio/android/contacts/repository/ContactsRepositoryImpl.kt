package com.picpay.desafio.android.contacts.repository

import com.picpay.desafio.android.contacts.datasource.ContactsLocalDataSource
import com.picpay.desafio.android.contacts.datasource.ContactsRemoteDataSource
import com.picpay.desafio.android.core_entity.User
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch
import java.io.IOException


class ContactsRepositoryImpl(
    private val remoteDataSource: ContactsRemoteDataSource,
    private val localDataSource: ContactsLocalDataSource,
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.IO
) : ContactsRepository {
    override fun getUsers(): Flow<List<User>> = flow {
        try {
            val result = remoteDataSource.getUsers()

            if (result.isSuccessful) {
                val users = result.body()!!
                coroutineScope {
                    launch {
                        localDataSource.saveContacts(users)
                    }
                }
                emit(users)
            } else {
                emit(listOf())
            }
        } catch (e: IOException) {
            val cache = localDataSource.getContacts()
            if (cache.isNotEmpty()) {
                emit(cache)
            }
        }
    }.flowOn(defaultDispatcher)
}