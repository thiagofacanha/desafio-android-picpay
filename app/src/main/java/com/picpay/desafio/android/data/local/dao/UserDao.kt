package com.picpay.desafio.android.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.picpay.desafio.android.data.local.model.LocalUser

@Dao
interface
UserDao {
    @Query("SELECT * FROM users")
    fun getAllUsers(): List<LocalUser>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(localUsers: List<LocalUser>)
}