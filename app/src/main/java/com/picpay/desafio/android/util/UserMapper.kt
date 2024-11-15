package com.picpay.desafio.android.util

import com.picpay.desafio.android.data.local.model.LocalUser
import com.picpay.desafio.android.data.remote.models.RemoteUser

object UserMapper {
    fun mapLocalToRemote(localUser: LocalUser): RemoteUser {
        return RemoteUser(
            id = localUser.id,
            name = localUser.name,
            username = localUser.username,
            img = localUser.img
        )
    }

    @Throws(InvalidRemoteUserException::class)
    fun mapRemoteToLocal(remoteUser: RemoteUser): LocalUser {
        if (remoteUser.isValid()) {
            return LocalUser(
                id = remoteUser.id!!,
                name = remoteUser.name!!,
                username = remoteUser.username!!,
                img = remoteUser.img!!
            )
        } else {
            throw InvalidRemoteUserException("RemoteUser has invalid fields.")
        }
    }
}