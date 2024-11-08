package com.picpay.desafio.android.ui

import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.R
import com.picpay.desafio.android.databinding.ActivityMainBinding
import com.picpay.desafio.android.ui.viewmodels.UserListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(R.layout.activity_main) {

    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: UserListAdapter
    private lateinit var layoutManager: LinearLayoutManager

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    private val userListViewModel: UserListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        recyclerView = binding.recyclerView
        progressBar = binding.userListProgressBar


        layoutManager = LinearLayoutManager(this)


        progressBar.visibility = View.VISIBLE
        userListViewModel.remoteUserList.observe(this) { users ->
            adapter = UserListAdapter(users)
            recyclerView.layoutManager = layoutManager
            recyclerView.adapter = adapter
            progressBar.visibility = View.GONE
            userListViewModel.getRecyclerViewState()
                ?.let { layoutManager.onRestoreInstanceState(it) }

        }
    }

    override fun onResume() {
        super.onResume()
        userListViewModel.getRecyclerViewState()?.let { layoutManager.onRestoreInstanceState(it) }
    }

    override fun onPause() {
        super.onPause()

        val recyclerViewState = layoutManager.onSaveInstanceState()
        recyclerViewState?.let { userListViewModel.saveRecyclerViewState(it) }
    }
}


