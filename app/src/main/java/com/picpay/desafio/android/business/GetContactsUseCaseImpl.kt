package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsLocalRepository
import com.picpay.desafio.android.repository.ContactsRemoteRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GetContactsUseCaseImpl(
    private val remoteRepository: ContactsRemoteRepository,
    private val localRepository: ContactsLocalRepository,
    private val dispatcher: CoroutineDispatcher
) : GetContactsUseCase {

    override fun invoke(): Flow<List<User>> = flow {

        val cache = localRepository.getContacts()
        if (cache.isNotEmpty()) {
            emit(cache)
        }

        val result = remoteRepository.getUsers()


        if (result.isSuccessful) {
            val users = result.body()!!
            coroutineScope {
                launch {
                    localRepository.saveContacts(users)
                }
            }
            emit(users)
        } else {
            emit(listOf())
        }


    }.flowOn(dispatcher)
}