package com.picpay.desafio.android.repository.service

import com.picpay.desafio.android.BuildConfig
import com.picpay.desafio.android.model.User
import okhttp3.OkHttpClient
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): Response<List<User>>

    companion object {
        fun create(): PicPayService {
            val client = OkHttpClient.Builder().build()
            return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL).client(client)
                .addConverterFactory(MoshiConverterFactory.create()).build()
                .create(PicPayService::class.java)
        }
    }
}