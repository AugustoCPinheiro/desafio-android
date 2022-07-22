package com.picpay.desafio.android.core_database.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user")
data class PersistenceUser(
    val img: String,
    val name: String,
    @PrimaryKey val id: Int,
    val username: String
)
