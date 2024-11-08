package com.picpay.desafio.android.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class LocalUser(
    @PrimaryKey val id: Int,
    val name: String,
    val username: String,
    val img: String
)