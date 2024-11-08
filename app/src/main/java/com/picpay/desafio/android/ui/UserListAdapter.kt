package com.picpay.desafio.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.data.remote.models.RemoteUser
import com.picpay.desafio.android.databinding.ListItemUserBinding

class UserListAdapter(private var remoteUsers: List<RemoteUser>) :
    RecyclerView.Adapter<UserListItemViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder(
            ListItemUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(remoteUsers[position])
    }

    override fun getItemCount(): Int = remoteUsers.size

    fun setItems(remoteUsers: List<RemoteUser>) {
        this.remoteUsers = remoteUsers
    }


}