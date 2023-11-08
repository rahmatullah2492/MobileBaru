package com.example.kelompokbaru.ui.splash_screen

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.login.MainActivity_Login
import com.example.kelompokbaru.ui.motion_layout.IntroActivity

class MainActivity_Splash_Screen : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private val splashtimeout:Long = 3000

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash_screen)

        supportActionBar?.hide() // menyembunyikan actionBar
        Handler(Looper.getMainLooper()).postDelayed({
            val sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
            val isLogged = sharedPreferences.getBoolean("isLogin", false)

<<<<<<< Updated upstream
            if (isLogged) {
                startActivity(Intent(this, MainActivity_Login::class.java))
            } else {
                startActivity(Intent(this, IntroActivity::class.java))
            }
=======
            // Ini pindah ke MainActivity_Login
            startActivity(Intent(this, IntroActivity::class.java))
>>>>>>> Stashed changes
            finish()
        }, 3000)
    }
}