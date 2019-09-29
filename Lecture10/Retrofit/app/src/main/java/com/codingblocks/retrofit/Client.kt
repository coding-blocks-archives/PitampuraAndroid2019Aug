package com.codingblocks.retrofit

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {

    val retrofit = Retrofit.Builder()
        .baseUrl("https://api.github.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    val api = retrofit.create(GithubService::class.java)
}