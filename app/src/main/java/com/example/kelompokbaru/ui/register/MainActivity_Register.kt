package com.example.kelompokbaru.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.kelompokbaru.MainActivity
import com.example.kelompokbaru.R
import com.example.kelompokbaru.ui.login.MainActivity_Login
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class MainActivity_Register : AppCompatActivity() {

    // variabel untuk referensi database firebase
    private lateinit var usernameEditText : EditText
    private lateinit var passwordEditText : EditText
    private lateinit var emailEditText : EditText
    private lateinit var githubEditText : EditText
    private lateinit var btnregister : Button
    private lateinit var database: DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_register)

        supportActionBar?.hide() //menyembunyikan actionbar

        val registerhref = findViewById<TextView>(R.id.LoginText)
        registerhref.setOnClickListener {
            val intent = Intent(this, MainActivity_Login::class.java)
            startActivity(intent)
        }

        usernameEditText = findViewById(R.id.inptUsername)
        passwordEditText = findViewById(R.id.inptPassword)
        emailEditText = findViewById(R.id.inptEmail)
        githubEditText = findViewById(R.id.inptGithub)
        btnregister = findViewById(R.id.btnRegister)

        // inisialisasi database dengan child "users"
        database = FirebaseDatabase.getInstance().getReference("users")

        // menambahkan listener untuk button sign up
        btnregister.setOnClickListener {
            // mengambil data dari EditText
            val username = usernameEditText.text.toString()
            val password = passwordEditText.text.toString()
            val email = emailEditText.text.toString()
            val github = githubEditText.text.toString()

            // membuat objek user dengan data yang diambil
            val user = User(username, password, email, github)

            // Buat instance dari FirebaseAuth
            val firebaseAuth = FirebaseAuth.getInstance()

            // Buat user baru dengan email dan password di Firebase Authentication
            firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        // Jika berhasil, tampilkan pesan Toast
                        Toast.makeText(
                            this@MainActivity_Register,
                            "Registrasi berhasil",
                            Toast.LENGTH_SHORT
                        ).show()
                        val intent = Intent(this@MainActivity_Register, MainActivity_Login::class.java)
                        startActivity(intent)
                    } else {
                        // Jika gagal, tampilkan pesan error
                        Toast.makeText(
                            this@MainActivity_Register,
                            "Gagal membuat user baru: ${task.exception?.message}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                }

            // menyimpan user ke database dengan username sebagai key
            database.child(username).setValue(user)
        }

        //bikin bingung
//        val btnlogin = findViewById<Button>(R.id.btnRegister)
//        btnlogin.setOnClickListener{
//            val intent = Intent(this, MainActivity_Login::class.java)
//            Toast.makeText(
//                this,
//                "Succes Register",
//                Toast.LENGTH_SHORT
//            ).show()
//            startActivity(intent)
//        }
    }
}