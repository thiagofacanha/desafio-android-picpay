package com.picpay.desafio.android

import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.data.remote.service.PicPayService

class ExampleService(
    private val service: PicPayService
) {

    suspend fun example(): List<RemoteUser> {
        val users = service.getUsers()

        return users
    }
}