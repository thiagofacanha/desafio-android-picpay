package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.models.User

interface UserRepository {
    suspend fun getAllUsers(): List<User>
}