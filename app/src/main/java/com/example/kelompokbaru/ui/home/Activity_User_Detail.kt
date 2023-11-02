package com.example.kelompokbaru.ui.home

import GitHubUser
import android.os.Bundle
import android.provider.ContactsContract.Profile
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.kelompokbaru.R
import com.squareup.picasso.Picasso
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Activity_User_Detail : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list_detail) // Gantilah dengan layout XML yang sesuai

        //val user = intent.getParcelableExtra<GitHubUser>("user")
        val login = intent.getStringExtra("login")
        login?.let {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(GitHubService::class.java)

            val call = service.getUserDetail(login)
            call.enqueue(object :Callback<GitHubUser>{
                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    if (response.isSuccessful){
                        val user = response.body()
                        val imgProfile = findViewById<ImageView>(R.id.Profile_Pojok_kiri_Detail)
                        val txtName = findViewById<TextView>(R.id.Teks_Detail)
                        val txtFollowers = findViewById<TextView>(R.id.Text_Followers)
                        val txtFollowing = findViewById<TextView>(R.id.Text_Following)

                        Picasso.get()
                            .load(user?.avatar_url)
                            .into(imgProfile)
                        txtName.text = user?.name
                        txtFollowers.text = user?.followers.toString()
                        txtFollowing.text = user?.following.toString()

                    }else{

                    }
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                    TODO("Not yet implemented")
                }
            })

        }





//        val btnFollow = findViewById<Button>(R.id.btn_follow)
//        btnFollow.setOnClickListener() {
//            if (btnFollow.text == "Follow"){
//                btnFollow.text == "Followed"
//                btnFollow.setBackgroundResource(R.drawable.baseline_clear_24)
//            } else{
//                btnFollow.text = "Follow"
//                btnFollow.setBackgroundResource(R.drawable.baseline_favorite_24)
//            }
        }

        }
   // }

