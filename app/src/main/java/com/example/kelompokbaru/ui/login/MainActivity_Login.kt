package com.example.kelompokbaru.ui.login

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract.CommonDataKinds.Email
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.kelompokbaru.MainActivity
import com.example.kelompokbaru.R
import com.example.kelompokbaru.databinding.ActivityMainLoginBinding
import com.example.kelompokbaru.databinding.ActivityMainRegisterBinding
import com.example.kelompokbaru.ui.dashboard.DashboardFragment
import com.example.kelompokbaru.ui.register.MainActivity_Register
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference

class MainActivity_Login : AppCompatActivity() {

    private lateinit var binding: ActivityMainLoginBinding
    private lateinit var databaseReference: DatabaseReference
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var firebaseAuth: FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //use view binding to inflate the layout and set the content
        binding = ActivityMainLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //menyembunyikan action bar
        supportActionBar?.hide()

        val registerhref = findViewById<TextView>(R.id.RegisterText)
        registerhref.setOnClickListener {
            val intent = Intent(this, MainActivity_Register::class.java)
            startActivity(intent)
        }

        //bikin bingung
//        val btnlogin = findViewById<Button>(R.id.loginbutton)
//        btnlogin.setOnClickListener{
//            val intent = Intent(this, MainActivity::class.java)
//            Toast.makeText(
//                this,
//                "Login berhasil",
//                Toast.LENGTH_SHORT
//            ).show()
//            startActivity(intent)
//        }

        //inisialisasi database references
        firebaseDatabase = FirebaseDatabase.getInstance()
        databaseReference = firebaseDatabase.reference.child("users")
        firebaseAuth = FirebaseAuth.getInstance()

//        menambahkan jika klik untuk tombol login yang akan memanggil metode loginUser dengan email dan kata sandi dari EditTexts
        binding.loginbutton.setOnClickListener {
            val email = binding.inputemail.text.toString()
            val password = binding.inputPass.text.toString()

            val emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+".toRegex()

            if (!email.matches(emailPattern)) {
                Toast.makeText(this@MainActivity_Login, "Email tidak valid", Toast.LENGTH_SHORT).show()
                //return from function
                return@setOnClickListener
            }
            if (email.isNotEmpty() && password.isNotEmpty()) {
                loginUser(email, password)
            } else {
                Toast.makeText(this@MainActivity_Login, "invalid", Toast.LENGTH_SHORT)
            }
        }
    }

    private fun loginUser(email: String, password: String) {
        // Masuk dengan email dan password di Firebase Authentication
        firebaseAuth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Jika berhasil, tampilkan pesan Toast
                    Toast.makeText(
                        this@MainActivity_Login,
                        "Login berhasil",
                        Toast.LENGTH_SHORT
                    ).show()
                    // Membuat intent untuk membuka MainActivity
                    val intent = Intent(this@MainActivity_Login, MainActivity::class.java)
                    // Mengirimkan data user ke MainActivity
                    intent.putExtra("email", email)
                    intent.putExtra("password", password)
                    // Memulai aktivitas baru
                    startActivity(intent)
                } else {
                    // Jika gagal, tampilkan pesan error
                    Toast.makeText(
                        this@MainActivity_Login,
                        "Gagal login: ${task.exception?.message}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
    }

//        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main_login)
//
//        supportActionBar?.hide()
//
//        // Setelah pengguna berhasil login, atur status login menjadi true.
//        val sharedPreferences = getSharedPreferences("MyPref", Context.MODE_PRIVATE)
//        val editor = sharedPreferences.edit()
//        editor.putBoolean("isLogin", true)
//        editor.apply()
//    }

}