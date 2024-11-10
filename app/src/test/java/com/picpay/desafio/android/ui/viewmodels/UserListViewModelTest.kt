package com.picpay.desafio.android.ui.viewmodels

import android.os.Parcelable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.Observer
import androidx.lifecycle.SavedStateHandle
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.util.remoteUser01
import com.picpay.desafio.android.util.remoteUser02
import com.picpay.desafio.android.util.remoteUser03
import com.picpay.desafio.android.util.remoteUser04
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.inOrder
import org.mockito.Mockito.mock
import org.mockito.Mockito.`when`
import org.mockito.junit.MockitoJUnitRunner

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class UserListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var loadingObserver: Observer<Boolean>

    private lateinit var savedStateHandle: SavedStateHandle
    private lateinit var userListViewModel: UserListViewModel


    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        savedStateHandle = SavedStateHandle()
        userListViewModel = UserListViewModel(userRepository, savedStateHandle)
        userListViewModel.loading.observeForever(loadingObserver)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }


    @Test
    fun shouldStartWithLoadingFalseAndGetUserListWithSuccessWhenCallingGetUserListFromViewModelInit() =
        runTest {
            val remoteUsers = listOf(
                remoteUser01,
                remoteUser02
            )
            `when`(userRepository.getAllUsers()).thenReturn(remoteUsers)

            advanceUntilIdle()

            Assert.assertEquals(remoteUsers, userListViewModel.remoteUserList.value)
            Assert.assertFalse(userListViewModel.loading.value ?: true)
        }

    @Test
    fun shouldSaveAndRetrieveRecyclerViewState() {
        val state: Parcelable = mock(Parcelable::class.java)
        userListViewModel.saveRecyclerViewState(state)

        Assert.assertEquals(state, userListViewModel.getRecyclerViewState())
    }

    @Test
    fun loadingStateShouldStartAsFalseAndChangeToTrueAfterCallingGetUserListFromViewModelInit() =
        runTest {
            val remoteUsers = listOf(
                remoteUser01,
                remoteUser02
            )
            `when`(userRepository.getAllUsers()).thenReturn(remoteUsers)
            val inOrder = inOrder(loadingObserver)
            inOrder.verify(loadingObserver).onChanged(true)
            advanceUntilIdle()
            inOrder.verify(loadingObserver).onChanged(false)
        }


    @Test
    fun shouldLoadAgainAllUsers() =
        runTest {
            val initialRemoteUsers = listOf(
                remoteUser01,
                remoteUser02
            )

            val finalRemoteUsers = listOf(
                remoteUser01,
                remoteUser02,
                remoteUser03,
                remoteUser04,
            )
            `when`(userRepository.getAllUsers()).thenReturn(initialRemoteUsers)

            advanceUntilIdle()

            Assert.assertEquals(initialRemoteUsers, userListViewModel.remoteUserList.value)
            advanceUntilIdle()
            `when`(userRepository.getAllUsers()).thenReturn(finalRemoteUsers)
            userListViewModel.getUserList()

            advanceUntilIdle()

            Assert.assertEquals(finalRemoteUsers, userListViewModel.remoteUserList.value)

        }


}
