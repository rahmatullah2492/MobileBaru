package com.example.kelompokbaru.ui.home

import GitHubUser
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface GitHubService {
    @GET("users")
    fun getUser(@Header("Authorization") authorization: String): Call<List<GitHubUser>>

    @GET("users/{login}")
    fun getUserDetail(@Path("login") login: String): Call<GitHubUser>


}