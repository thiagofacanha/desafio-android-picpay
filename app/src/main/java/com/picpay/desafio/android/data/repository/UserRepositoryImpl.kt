package com.picpay.desafio.android.data.repository

import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.util.UserMapper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException

class UserRepositoryImpl(private val userDao: UserDao, private val picPayService: PicPayService) :
    UserRepository {
    override suspend fun getAllUsers(): List<RemoteUser> = withContext(Dispatchers.IO) {
        try {
            val apiUsers = picPayService.getUsers()
            apiUsers.filter { it.isValid() }//Maybe we could do an deserializer to return this invalid registers but with some value set like "invalid value" instead of null. Can check later how do this.
            userDao.insertAll(apiUsers.map { UserMapper.mapRemoteToLocal(it) })
            apiUsers
        } catch (e: IOException) {
            getLocalUsers()
        } catch (e: HttpException) {
            getLocalUsers()
        } catch (e: Exception) { //keeping this while cant be sure if I checked all possible exceptions
            getLocalUsers()
        }
    }

    private fun getLocalUsers() = userDao.getAllUsers().map {
        UserMapper.mapLocalToRemote(it)
    }

    override suspend fun insertAll(remoteUsers: List<RemoteUser>) = withContext(Dispatchers.IO) {
        userDao.insertAll(remoteUsers.map { UserMapper.mapRemoteToLocal(it) })
    }
}