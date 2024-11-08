package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.remote.models.RemoteUser

interface UserRepository {
    suspend fun getAllUsers(): List<RemoteUser>

    suspend fun insertAll(remoteUsers: List<RemoteUser>)
}