package com.picpay.desafio.android.repository.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.model.User

@Database(entities = [User::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun createDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "picpay.db")
                .build()

    }
}
