package com.picpay.desafio.android.core_database.di

import com.picpay.desafio.android.core_database.db.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val persistenceModule = module {
    single { AppDatabase.createDatabase(androidContext()) }
    single { get<AppDatabase>().userDao() }
}

val databaseModule = persistenceModule