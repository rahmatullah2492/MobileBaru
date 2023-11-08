package com.example.kelompokbaru.ui.favorite

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.databse.User
import com.example.kelompokbaru.ui.home.Activity_User_Detail


import com.squareup.picasso.Picasso

class FollowedUserAdapter : RecyclerView.Adapter<FollowedUserAdapter.FollowedUserViewHolder>() {
    private var followedUsers: List<User> = listOf()

    fun setFollowedUsers(users: List<User>){
        followedUsers = users
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowedUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_favorite, parent, false)
        return FollowedUserViewHolder(view)
    }

    override fun onBindViewHolder(holder: FollowedUserViewHolder, position: Int) {
        val followedUser = followedUsers[position]
        holder.bind(followedUser)

    }

    override fun getItemCount(): Int {
        return followedUsers.size
    }

    class FollowedUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(followedUser: User) {
            //val randomStatus = if (Math.random() < 0.5) "Online" else "Offline"

            Picasso.get().load(followedUser.avatarUrl).into(itemView.findViewById<ImageView>(R.id.Foto_Favorite))
            itemView.findViewById<TextView>(R.id.Nama_Favorite).text = followedUser.login

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, Activity_User_Detail::class.java)
                intent.putExtra("login", followedUser.login)
                intent.putExtra("avatar_url", followedUser.avatarUrl)
                itemView.context.startActivity(intent)
            }


        }
    }
}
