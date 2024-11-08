package com.picpay.desafio.android.data.remote.service

import com.picpay.desafio.android.data.remote.models.RemoteUser
import retrofit2.http.GET


interface PicPayService {

    @GET("users")
    suspend fun getUsers(): List<RemoteUser>

    companion object {
        //        const val BASE_URL = "https://api.picpay.com12313/"
        const val BASE_URL = "https://609a908e0f5a13001721b74e.mockapi.io/picpay/api/"
    }
}