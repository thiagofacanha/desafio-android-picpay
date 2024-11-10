package com.picpay.desafio.android.util

import com.picpay.desafio.android.data.local.model.LocalUser
import com.picpay.desafio.android.data.remote.models.RemoteUser


val localUser01 = LocalUser(1, "Name01", "userName01", "img/url01")
val remoteUser01 = RemoteUser(1, "Name01", "userName01", "img/url01")
val remoteUser02 = RemoteUser(2, "Name02", "userName02", "img/url02")
val remoteUser03 = RemoteUser(3, "Name03", "userName03", "img/url03")
val remoteUser04 = RemoteUser(4, "Name04", "userName04", "img/url04")

val invalidBlankNameRemoteUser = RemoteUser(2, "", "userName02", "img/url02")
val invalidNullNameRemoteUser = RemoteUser(2, null, "userName02", "img/url02")