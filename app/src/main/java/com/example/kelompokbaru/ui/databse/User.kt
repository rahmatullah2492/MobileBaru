package com.example.kelompokbaru.ui.databse

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "followed_users")
data class User(
    @PrimaryKey(autoGenerate = true) val id: Int= 0,
    val login: String,
    val avatarUrl: String,
    val email: String
)