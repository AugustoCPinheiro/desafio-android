package com.picpay.desafio.android.core_database.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.core_database.model.PersistenceUser


@Dao
interface UserDao {
    @Query("SELECT * FROM user")
    fun getAll(): List<PersistenceUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg users: PersistenceUser)
}