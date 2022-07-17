package com.picpay.desafio.android.di

import com.picpay.desafio.android.business.GetContactsUseCase
import com.picpay.desafio.android.business.GetContactsUseCaseImpl
import com.picpay.desafio.android.repository.ContactsRemoteRepository
import com.picpay.desafio.android.repository.ContactsRemoteRepositoryImpl
import com.picpay.desafio.android.repository.db.AppDatabase
import com.picpay.desafio.android.repository.service.PicPayService
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import kotlinx.coroutines.Dispatchers
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val contactsModule = module {
    single { PicPayService.create() }
    single<ContactsRemoteRepository> { ContactsRemoteRepositoryImpl(get()) }
    factory<GetContactsUseCase> { GetContactsUseCaseImpl(get(), get(), Dispatchers.IO) }
    single { AppDatabase.createDatabase(androidContext()) }
    single { get<AppDatabase>().userDao() }
    viewModel { ContactsViewModel(get(), Dispatchers.Main) }
}