package com.picpay.desafio.android.core_network.di

import com.picpay.desafio.android.core_network.service.PicPayService
import org.koin.dsl.module

val serviceModule = module {
    single { PicPayService.create() }
}

val networkModules =  serviceModule