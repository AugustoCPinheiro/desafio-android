package com.picpay.desafio.android.di

import com.picpay.desafio.android.business.ContactsBusiness
import com.picpay.desafio.android.repository.ContactsRepository
import com.picpay.desafio.android.repository.service.PicPayService
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    single { PicPayService.create() }
    single { ContactsRepository(get()) }
    single { ContactsBusiness(get()) }
    viewModel { ContactsViewModel(get()) }
}