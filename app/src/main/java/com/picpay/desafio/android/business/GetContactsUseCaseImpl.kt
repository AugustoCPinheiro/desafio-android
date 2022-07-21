package com.picpay.desafio.android.business

import com.picpay.desafio.android.model.User
import com.picpay.desafio.android.repository.ContactsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

class GetContactsUseCaseImpl(
    private val contactsRepository: ContactsRepository,
) : GetContactsUseCase {

    override fun invoke(): Flow<List<User>> = contactsRepository.getUsers()
}