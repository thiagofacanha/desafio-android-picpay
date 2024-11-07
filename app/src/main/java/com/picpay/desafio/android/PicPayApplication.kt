package com.picpay.desafio.android

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class PicPayApplication : Application() {
    override fun onCreate() {
        super.onCreate()

    }
}