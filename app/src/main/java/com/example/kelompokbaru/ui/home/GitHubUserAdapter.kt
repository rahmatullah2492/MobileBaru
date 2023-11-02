package com.example.kelompokbaru.ui.home

import GitHubUser
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompokbaru.R
import com.squareup.picasso.Picasso

class GitHubUserAdapter : RecyclerView.Adapter<GitHubUserAdapter.GitHubUserViewHolder>() {

    private var userList: List<GitHubUser> = ArrayList()
    private var filteredList: List<GitHubUser> = ArrayList() // filter search

    fun setUserList(users: List<GitHubUser>){
        userList = users
        filteredList = users // filter search
        notifyDataSetChanged()
    }

    //filter search
    fun filter(query: String) {
        filteredList = userList.filter { user ->
            user.login.contains(query, ignoreCase = true)
        }
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GitHubUserViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_list_user, parent, false)
        return GitHubUserViewHolder(view)
    }
    override fun onBindViewHolder(holder: GitHubUserViewHolder, position: Int) {
        //val user = userList[position]
        val user = filteredList[position]
        holder.bind(user)
    }
    override fun getItemCount(): Int {
        return filteredList.size
        //return userList.size
    }

    inner class GitHubUserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(user: GitHubUser) {

            itemView.findViewById<TextView>(R.id.Text_Name).text = user.login
            itemView.findViewById<TextView>(R.id.Text_Deskripsi).text = user.starred_url
            Picasso.get().load(user.avatar_url).into(itemView.findViewById<ImageView>(R.id.Image_Post))
            Picasso.get().load(user.avatar_url).into(itemView.findViewById<ImageView>(R.id.Profile))

            itemView.setOnClickListener {
                val intent = Intent(itemView.context, Activity_User_Detail::class.java)
                intent.putExtra("login", user.login)
                itemView.context.startActivity(intent)
            }

        }
    }

}