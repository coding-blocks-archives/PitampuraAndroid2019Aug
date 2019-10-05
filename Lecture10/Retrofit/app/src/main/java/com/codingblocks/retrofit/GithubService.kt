package com.codingblocks.retrofit

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{name}")
    fun getUser(@Path("name") name: String): Call<User>

    @GET("users")
    fun getAllUsers(): Call<List<User>>

}