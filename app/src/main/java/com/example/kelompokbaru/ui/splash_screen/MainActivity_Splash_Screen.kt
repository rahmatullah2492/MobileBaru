package com.example.kelompokbaru.ui.splash_screen

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.example.kelompokbaru.MainActivity
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.motion_layout.IntroActivity

class MainActivity_Splash_Screen : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_splash_screen)


        supportActionBar?.hide() // menyembunyikan actionBar

        Handler(Looper.getMainLooper()).postDelayed({
            // Ini pindah ke MainActivity_Login
            startActivity(Intent(this, IntroActivity::class.java))
            finish()
        }, 3000) // Delay selama 3 detik
    }
}