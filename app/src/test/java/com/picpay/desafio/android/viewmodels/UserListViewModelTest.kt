package com.picpay.desafio.android.viewmodels

import android.os.Parcelable
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.data.repository.UserRepository
import com.picpay.desafio.android.ui.viewmodels.UserListViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule
import org.mockito.Mock
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.`when`

@ExperimentalCoroutinesApi
class UserListViewModelTest {

    @get:Rule
    var rule: TestRule = InstantTaskExecutorRule()

    @Mock
    private lateinit var userRepository: UserRepository

    @Mock
    private lateinit var savedStateHandle: SavedStateHandle

    private lateinit var viewModel: UserListViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        viewModel = UserListViewModel(userRepository, savedStateHandle)
    }

    @Test
    fun test01() = runTest {
        val users = listOf(RemoteUser(id = 1, name = "User 1", username = "user1", img = "img1"))
        `when`(userRepository.getAllUsers()).thenReturn(users)

        viewModel.getUserList()

        assertEquals(users, viewModel.remoteUserList.value)
        verify(userRepository).getAllUsers()
    }

    @Test
    fun test02() {
        val state = mock(Parcelable::class.java)
        viewModel.saveRecyclerViewState(state)
        verify(savedStateHandle)[UserListViewModel.RECYCLER_VIEW_STATE] = state
    }

    @Test
    fun test03() {
        val state = mock(Parcelable::class.java)
        `when`(savedStateHandle.get<Parcelable>(UserListViewModel.RECYCLER_VIEW_STATE)).thenReturn(
            state
        )

        val result = viewModel.getRecyclerViewState()

        assertEquals(state, result)
    }
}
