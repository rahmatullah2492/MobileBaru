package com.example.kelompokbaru.ui.databse

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface UserDao {

    @Query("SELECT * FROM followed_users WHERE login = :login AND email= :email")
    fun getUserByLogin(login: String, email: String): LiveData<User>

    @Query("SELECT * FROM followed_users WHERE email = :email")
    fun getUserByEmail(email: String): LiveData<List<User>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertFavorite(user: User)

    @Query("DELETE FROM followed_users WHERE login = :login AND email = :email")
    suspend fun deleteUserByLogin(login: String, email: String)



}