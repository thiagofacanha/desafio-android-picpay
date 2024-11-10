package com.picpay.desafio.android.di

import android.content.Context
import androidx.lifecycle.SavedStateHandle
import androidx.room.Room
import com.picpay.desafio.android.data.local.PicPayDatabase
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.data.repository.UserRepositoryImpl
import com.picpay.desafio.android.ui.viewmodels.UserListViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UserModule::class]
)
object TestUserModule {

    @Provides
    @Singleton
    fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl("http://localhost:8080")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providePicPayService(retrofit: Retrofit): PicPayService {
        return retrofit.create(PicPayService::class.java)
    }

    @Provides
    @Singleton
    fun providePicPayDatabase(@ApplicationContext context: Context): PicPayDatabase {
        return Room.inMemoryDatabaseBuilder(context, PicPayDatabase::class.java)
            .allowMainThreadQueries()
            .build()
    }

    @Provides
    fun provideUserDao(appDatabase: PicPayDatabase): UserDao {
        return appDatabase.userDao()
    }

    @Provides
    @Singleton
    fun provideUserRepository(userDao: UserDao, service: PicPayService): UserRepository {
        return UserRepositoryImpl(userDao, service)
    }

    @Provides
    @Singleton
    fun provideSavedStateHandle(): SavedStateHandle {
        return SavedStateHandle()
    }

    @Provides
    @Singleton
    fun provideUserListViewModel(userRepository: UserRepository, savedStateHandle: SavedStateHandle): UserListViewModel {
        return UserListViewModel(userRepository, savedStateHandle)
    }
}
