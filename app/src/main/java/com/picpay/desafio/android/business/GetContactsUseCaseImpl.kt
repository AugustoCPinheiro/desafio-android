package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRemoteRepository
import com.picpay.desafio.android.repository.db.UserDao
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch

class GetContactsUseCaseImpl(
    private val repository: ContactsRemoteRepository,
    private val userDao: UserDao,
    private val dispatcher: CoroutineDispatcher
) : GetContactsUseCase {

    override fun invoke(): Flow<List<User>> = flow {
        val result = repository.getUsers()

        if (result.isSuccessful) {
            val users = result.body()!!
            coroutineScope {
                launch(dispatcher) {
                    userDao.insertAll(*users.toTypedArray())
                }
            }
            emit(users)
        } else {
            emit(listOf())
        }
    }
}