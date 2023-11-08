package com.example.kelompokbaru.ui.home

import GitHubUser
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.example.kelompokbaru.MainActivity
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.databse.AppDatabase
import com.example.kelompokbaru.ui.databse.User

import com.squareup.picasso.Picasso
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

//class Activity_User_Detail : AppCompatActivity() {
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//
//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.item_list_detail)
//
//        //val user = intent.getParcelableExtra<GitHubUser>("user")
//        val login = intent.getStringExtra("login")
//        login?.let {
//            val retrofit = Retrofit.Builder()
//                .baseUrl("https://api.github.com/")
//                .addConverterFactory(GsonConverterFactory.create())
//                .build()
//
//            val service = retrofit.create(GitHubService::class.java)
//
//            val call = service.getUserDetail(login)
//            call.enqueue(object :Callback<GitHubUser>{
//                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
//                    if (response.isSuccessful) {
//                        val user = response.body()
//                        val imgProfile = findViewById<ImageView>(R.id.Profile_Pojok_kiri_Detail)
//                        val txtName = findViewById<TextView>(R.id.Teks_Detail)
//                        val txtFollowers = findViewById<TextView>(R.id.Text_Followers)
//                        val txtFollowing = findViewById<TextView>(R.id.Text_Following)
//
//                        Picasso.get().load(user?.avatar_url).into(imgProfile)
//                        txtName.text = user?.name
//                        txtFollowers.text = user?.followers.toString()
//                        txtFollowing.text = user?.following.toString()
//
//
//                    } else {
//
//                    }
//                }
//
//                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
//                    TODO("Not yet implemented")
//                }
//            })
//
//        }
//    }
//}
//



class Activity_User_Detail : AppCompatActivity() {

    private lateinit var db: AppDatabase
    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.item_list_detail)

        val followButton = findViewById<Button>(R.id.followButton)
        val login = intent.getStringExtra("login")
        val avatar_url = intent.getStringExtra("avatar_url")
        sharedPreferences = getSharedPreferences("user_data", Context.MODE_PRIVATE)

        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "users").build()
        val favoriteUserDao = db.UserDao()

        login?.let {
            val retrofit = Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            val service = retrofit.create(GitHubService::class.java)

            val call = service.getUserDetail(login)
            call.enqueue(object : Callback<GitHubUser> {
                override fun onResponse(call: Call<GitHubUser>, response: Response<GitHubUser>) {
                    if (response.isSuccessful) {
                        val user = response.body()
                        val imgProfile = findViewById<ImageView>(R.id.Profile_Pojok_kiri_Detail)
                        val txtName = findViewById<TextView>(R.id.Teks_Detail)
                        val txtFollowers = findViewById<TextView>(R.id.Text_Followers)
                        val txtFollowing = findViewById<TextView>(R.id.Text_Following)

                        Picasso.get().load(user?.avatar_url).into(imgProfile)
                        txtName.text = user?.name
                        txtFollowers.text = user?.followers.toString()
                        txtFollowing.text = user?.following.toString()
                    } else {
                        // Penanganan kesalahan jika respon tidak berhasil
                    }
                }

                override fun onFailure(call: Call<GitHubUser>, t: Throwable) {
                    // Penanganan kesalahan saat terjadi kegagalan koneksi
                }
            })
        }


        favoriteUserDao.getUserByLogin(login ?: "", getEmailFromSharedPreferences())
            .observe(this) { user ->
                if (user != null && user.login == login && user.email == getEmailFromSharedPreferences()) {
                    followButton.text = "Followed"
                    followButton.setBackgroundResource(R.drawable.baseline_bookmark_24)
                } else {
                    followButton.text = "Follow"
                    followButton.setBackgroundResource(R.drawable.baseline_bookmark_border_24)
                }
            }

        followButton.setOnClickListener {
            lifecycleScope.launch {
                val user = favoriteUserDao.getUserByLogin(
                    login ?: "",
                    getEmailFromSharedPreferences()
                ).value

                if (followButton.text == "Follow") {
                    user ?: favoriteUserDao.insertFavorite(
                        User(
                            login = login.orEmpty(),
                            avatarUrl = avatar_url.orEmpty(),
                            email = getEmailFromSharedPreferences()
                        )
                    )
                    followButton.text = "Followed"
                    followButton.setBackgroundResource(R.drawable.baseline_bookmark_24)
                } else {
                    login?.let {
                        favoriteUserDao.deleteUserByLogin(
                            login ?: "",
                            getEmailFromSharedPreferences()
                        )
                    }
                    followButton.text = "Follow"
                    followButton.setBackgroundResource(R.drawable.baseline_bookmark_border_24)
                }
            }
        }
    }
    private fun getEmailFromSharedPreferences(): String {
       return sharedPreferences.getString("user_email", "") ?: ""
        //return "rahmat@gmail.com"
    }

    override fun onBackPressed() {
        super.onBackPressed()
        val intent = Intent(this@Activity_User_Detail, MainActivity::class.java)
        startActivity(intent)
    }
}


