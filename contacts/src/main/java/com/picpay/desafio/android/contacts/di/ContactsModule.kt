package com.picpay.desafio.android.di

import com.picpay.desafio.android.contacts.business.GetContactsUseCase
import com.picpay.desafio.android.contacts.datasource.ContactsLocalDataSource
import com.picpay.desafio.android.contacts.datasource.ContactsLocalDataSourceImpl
import com.picpay.desafio.android.contacts.datasource.ContactsRemoteDataSource
import com.picpay.desafio.android.contacts.datasource.ContactsRemoteDataSourceImpl
import com.picpay.desafio.android.contacts.repository.ContactsRepository
import com.picpay.desafio.android.contacts.repository.ContactsRepositoryImpl
import com.picpay.desafio.android.contacts.ui.ContactsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val repositoryModule = module {
    single<ContactsRepository> { ContactsRepositoryImpl(get(), get()) }
}

val dataSourceModule = module {
    single<ContactsRemoteDataSource> { ContactsRemoteDataSourceImpl(get()) }
    single<ContactsLocalDataSource> { ContactsLocalDataSourceImpl(get()) }
}
val businessModule = module {
    factory { GetContactsUseCase(get()) }
}


val viewModelModule = module {
    viewModel { ContactsViewModel(get()) }
}

val contactsModule = repositoryModule + businessModule + viewModelModule + dataSourceModule