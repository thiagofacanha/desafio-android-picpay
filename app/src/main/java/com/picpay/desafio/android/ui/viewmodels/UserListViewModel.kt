package com.picpay.desafio.android.ui.viewmodels

import android.os.Parcelable
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val _remoteUserList by lazy { MutableLiveData<List<RemoteUser>>() }
    val remoteUserList: LiveData<List<RemoteUser>> get() = _remoteUserList

    private val _loading = MutableLiveData(false)
    val loading: LiveData<Boolean> get() = _loading

    init {
        getUserList()
    }

    fun getUserList() {
        _loading.value = true
        viewModelScope.launch {
            _remoteUserList.value = userRepository.getAllUsers()
            _loading.value = false
        }
    }


    fun saveRecyclerViewState(state: Parcelable) {
        savedStateHandle[RECYCLER_VIEW_STATE] = state
    }

    fun getRecyclerViewState(): Parcelable? {
        return savedStateHandle[RECYCLER_VIEW_STATE]
    }

    companion object {
        const val RECYCLER_VIEW_STATE = "recycler_view_state"
    }
}
