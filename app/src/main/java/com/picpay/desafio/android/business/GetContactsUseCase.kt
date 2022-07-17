package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRemoteRepository
import com.picpay.desafio.android.repository.db.AppDatabase
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlin.coroutines.coroutineContext

interface GetContactsUseCase {
    operator fun invoke(): Flow<List<User>>
}