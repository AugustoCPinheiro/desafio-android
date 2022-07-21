package com.picpay.desafio.android.di

import com.picpay.desafio.android.business.GetContactsUseCase
import com.picpay.desafio.android.datasource.ContactsLocalDataSource
import com.picpay.desafio.android.datasource.ContactsLocalDataSourceImpl
import com.picpay.desafio.android.datasource.ContactsRemoteDataSource
import com.picpay.desafio.android.datasource.ContactsRemoteDataSourceImpl
import com.picpay.desafio.android.repository.ContactsRepository
import com.picpay.desafio.android.repository.ContactsRepositoryImpl
import com.picpay.desafio.android.datasource.db.AppDatabase
import com.picpay.desafio.android.datasource.service.PicPayService
import com.picpay.desafio.android.ui.contacts.ContactsViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val serviceModule = module {
    single { PicPayService.create() }
}

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

val databaseModule = module {
    single { AppDatabase.createDatabase(androidContext()) }
    single { get<AppDatabase>().userDao() }
}

val viewModelModule = module {
    viewModel { ContactsViewModel(get()) }
}

val contactsModule =
    serviceModule + repositoryModule + businessModule + databaseModule + viewModelModule + dataSourceModule