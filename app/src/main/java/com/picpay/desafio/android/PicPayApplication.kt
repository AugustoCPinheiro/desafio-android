package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.core_database.di.databaseModule
import com.picpay.desafio.android.core_network.di.networkModules
import com.picpay.desafio.android.di.contactsModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@PicPayApplication)
            modules(contactsModule + networkModules + databaseModule)
        }
    }
}