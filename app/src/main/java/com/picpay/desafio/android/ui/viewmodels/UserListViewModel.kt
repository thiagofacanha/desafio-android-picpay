package com.picpay.desafio.android.ui.viewmodels

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.models.User
import com.picpay.desafio.android.repositories.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _userList by lazy { MutableLiveData<List<User>>() }
    val userList: LiveData<List<User>> get() = _userList

    init {
        getUserList()
    }

    fun getUserList() {
        viewModelScope.launch {
            _userList.value = listOf(
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("werwer", "d1", 1, "tetwer"),
                User("wqeweqwerweaeqr", "d2", 2, "tasdasfadsg"),
                User("qwweqweacccwer", "d3", 3, "tesfaweqweeqwe")
            )//userRepository.getAllUsers()
        }
    }

    fun saveRecyclerViewState(state: Parcelable) {
        savedStateHandle[RECYCLER_VIEW_STATE] = state
    }

    fun getRecyclerViewState(): Parcelable? {
        return savedStateHandle[RECYCLER_VIEW_STATE]
    }

    companion object {
        private const val RECYCLER_VIEW_STATE = "recycler_view_state"
    }
}
