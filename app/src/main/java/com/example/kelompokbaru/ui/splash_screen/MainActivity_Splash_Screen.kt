package com.example.kelompokbaru.ui.splash_screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kelompokbaru.MainActivity
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.login.MainActivity_Login
import com.example.kelompokbaru.ui.motion_layout.IntroActivity

class MainActivity_Splash_Screen : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val SPALSH_TIME_OUT:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash_screen)

        supportActionBar?.hide() // menyembunyikan actionBar
        Handler(Looper.getMainLooper()).postDelayed({
            sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val isLogged = sharedPreferences.getBoolean("isLogin", false)

            if (isLogged) {
                startActivity(Intent(this, MainActivity::class.java))
            } else {
                startActivity(Intent(this, IntroActivity::class.java))
            }
            finish()
        }, SPALSH_TIME_OUT)
    }

//    private fun checkSharedPreferences() {
//        val userEmail = sharedPreferences.contains("user_email")
//
//        val intent = if (userEmail) {
//            Intent(this, MainActivity::class.java)
//        } else {
//            Intent(this, IntroActivity::class.java)
//        }
//        startActivity(intent)
//        finish()
//
//    }
}