package com.picpay.desafio.android.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass


@JsonClass(generateAdapter = true)
@Entity(tableName = "user")
data class User(
    @Json(name = "img") val img: String,
    @Json(name = "name") val name: String,
    @Json(name = "id") @PrimaryKey val id: Int,
    @Json(name = "username") val username: String
)