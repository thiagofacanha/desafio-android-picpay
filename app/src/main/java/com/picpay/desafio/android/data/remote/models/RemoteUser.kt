package com.picpay.desafio.android.data.remote.models

import com.google.gson.annotations.SerializedName

data class RemoteUser(
    @SerializedName("id") val id: Int?,
    @SerializedName("name") val name: String?,
    @SerializedName("username") val username: String?,
    @SerializedName("img") val img: String?
) {
    fun isValid() =
        !img.isNullOrBlank() && !name.isNullOrBlank() && id != null && id > -1 && !username.isNullOrBlank()
}