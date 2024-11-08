package com.picpay.desafio.android.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.local.model.LocalUser

@Database(entities = [LocalUser::class], version = 1)

abstract class PicPayDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}