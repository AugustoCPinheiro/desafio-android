package com.picpay.desafio.android.contacts.business

import com.picpay.desafio.android.core_entity.User
import com.picpay.desafio.android.contacts.repository.ContactsRepository
import kotlinx.coroutines.flow.Flow

class GetContactsUseCase(
    private val contactsRepository: ContactsRepository,
) {

    operator fun invoke(): Flow<List<User>> = contactsRepository.getUsers()
}