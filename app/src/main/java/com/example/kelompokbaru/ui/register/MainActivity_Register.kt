package com.example.kelompokbaru.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.login.MainActivity_Login

class MainActivity_Register : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        supportActionBar?.hide() //menyembunyikan actionbar

        val registerhref = findViewById<TextView>(R.id.LoginText)
        registerhref.setOnClickListener {
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }
    }
}