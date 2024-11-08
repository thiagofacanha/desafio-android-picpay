package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.util.UserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserRepositoryImpl(private val userDao: UserDao, private val picPayService: PicPayService) :
    UserRepository {
    override suspend fun getAllUsers(): List<RemoteUser>  = withContext(Dispatchers.IO){
        val cachedUsers = userDao.getAllUsers()//TODO Check a better approach. This will always keep the local as result.
        if (cachedUsers.isNotEmpty()) {
            cachedUsers.map {
                UserMapper.mapLocalToRemote(it)
            }
        } else {
            val apiUsers = picPayService.getUsers()
            userDao.insertAll(apiUsers.map { UserMapper.mapRemoteToLocal(it) })
            apiUsers
        }
    }

    override suspend fun insertAll(remoteUsers: List<RemoteUser>) = withContext(Dispatchers.IO) {
        userDao.insertAll(remoteUsers.map { UserMapper.mapRemoteToLocal(it) })
    }
}