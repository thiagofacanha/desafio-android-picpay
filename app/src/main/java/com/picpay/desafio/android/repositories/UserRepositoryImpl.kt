package com.picpay.desafio.android.repositories

import com.picpay.desafio.android.service.PicPayService
import com.picpay.desafio.android.models.User

class UserRepositoryImpl(private val picPayService: PicPayService) : UserRepository {
    override suspend fun getAllUsers(): List<User> = picPayService.getUsers()
}