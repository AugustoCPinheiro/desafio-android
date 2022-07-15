package com.picpay.desafio.android.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize


@Parcelize
@Entity(tableName = "user")
data class User(
    @SerializedName("img") val img: String,
    @SerializedName("name") val name: String,
    @SerializedName("id") @PrimaryKey val id: Int,
    @SerializedName("username") val username: String
) : Parcelable