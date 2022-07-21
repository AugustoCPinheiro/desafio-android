package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import kotlinx.coroutines.flow.Flow

interface GetContactsUseCase {
    operator fun invoke(): Flow<List<User>>
}