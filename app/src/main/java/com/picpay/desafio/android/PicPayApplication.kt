package com.picpay.desafio.android

import android.app.Application
import com.picpay.desafio.android.di.contactsModule
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class PicPayApplication: Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            modules(contactsModule)
        }
    }
}