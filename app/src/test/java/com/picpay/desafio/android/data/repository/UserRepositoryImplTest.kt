package com.picpay.desafio.android.data.repository

import com.nhaarman.mockitokotlin2.doThrow
import com.picpay.desafio.android.data.local.dao.UserDao
import com.picpay.desafio.android.data.remote.service.PicPayService
import com.picpay.desafio.android.util.UserMapper
import com.picpay.desafio.android.util.remoteUser01
import com.picpay.desafio.android.util.remoteUser02
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers.anyList
import org.mockito.Mock
import org.mockito.Mockito.doAnswer
import org.mockito.Mockito.never
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner
import retrofit2.HttpException
import java.io.IOException

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserRepositoryImplTest {

    @Mock
    private lateinit var userDao: UserDao

    @Mock
    private lateinit var picPayService: PicPayService


    private lateinit var userRepository: UserRepositoryImpl

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        userRepository = UserRepositoryImpl(userDao, picPayService)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun shouldReturnGetAllUsersSuccessAndCallInsertAllUsersLocally() = runTest {
        val remoteUsers = listOf(
            remoteUser01, remoteUser02
        )
        `when`(picPayService.getUsers()).thenReturn(remoteUsers)

        val result = userRepository.getAllUsers()

        assertEquals(remoteUsers, result)
        verify(userDao).insertAll(remoteUsers.map { UserMapper.mapRemoteToLocal(it) })
    }

    @Test
    fun shouldNeverCallInsertAllWhenThrowingRuntimeExceptionAndShouldCallGetLocalUsers() = runTest {
        val localUsers =
            listOf(remoteUser01)

        `when`(picPayService.getUsers()).thenThrow(RuntimeException())


        `when`(userDao.getAllUsers()).thenReturn(localUsers.map { UserMapper.mapRemoteToLocal(it) })
        val result = userRepository.getAllUsers()
        assertEquals(localUsers, result)
        verify(userDao, never()).insertAll(anyList())
    }

    @Test
    fun shouldNeverCallInsertAllWhenThrowingHttpExceptionAndShouldCallGetLocalUsers() = runTest {
        val localUsers = listOf(
            remoteUser01,
        )
        doThrow(HttpException::class).`when`(picPayService).getUsers()
        `when`(userDao.getAllUsers()).thenReturn(localUsers.map { UserMapper.mapRemoteToLocal(it) })

        val result = userRepository.getAllUsers()

        assertEquals(localUsers, result)
        verify(userDao, never()).insertAll(anyList())
    }

    @Test
    fun shouldNeverCallInsertAllWhenThrowingIOExceptionAndShouldCallGetLocalUsers() = runTest {
        val localUsers = listOf(
            remoteUser01,
        )
        doAnswer { throw IOException() }.`when`(picPayService).getUsers()
        `when`(userDao.getAllUsers()).thenReturn(localUsers.map { UserMapper.mapRemoteToLocal(it) })

        val result = userRepository.getAllUsers()

        assertEquals(localUsers, result)
        verify(userDao, never()).insertAll(anyList())
    }


    @Test
    fun shouldCallInsertAllWhenReceivingAListOfRemoteUsers() = runTest {
        val remoteUsers = listOf(
            remoteUser01,
            remoteUser02
        )

        userRepository.insertAll(remoteUsers)

        verify(userDao).insertAll(anyList())
    }
}
