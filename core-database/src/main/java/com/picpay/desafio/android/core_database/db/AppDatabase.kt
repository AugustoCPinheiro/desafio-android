package com.picpay.desafio.android.core_database.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.picpay.desafio.android.core_database.model.PersistenceUser
import com.picpay.desafio.android.core_entity.User

@Database(entities = [PersistenceUser::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        fun createDatabase(context: Context): AppDatabase =
            Room.databaseBuilder(context, AppDatabase::class.java, "picpay.db")
                .build()

    }
}
