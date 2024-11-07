package com.picpay.desafio.android.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.picpay.desafio.android.databinding.ListItemUserBinding
import com.picpay.desafio.android.models.User

class UserListAdapter(var users: List<User>) : RecyclerView.Adapter<UserListItemViewHolder>() {

//    var users = emptyList<User>()
//        set(value) {
//            val result = DiffUtil.calculateDiff(
//                UserListDiffCallback(
//                    field,
//                    value
//                )
//            )
//            result.dispatchUpdatesTo(this)
//            field = value
//        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListItemViewHolder {
        return UserListItemViewHolder(
            ListItemUserBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: UserListItemViewHolder, position: Int) {
        holder.bind(users[position])
    }

    override fun getItemCount(): Int = users.size

    fun setItems(users: List<User>){
        this.users = users
    }


}