package com.puldroid.architecturepattern.REST

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object Client {
    val retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl("https://api.github.com")
        .build()

    val service = retrofit.create(GithubService::class.java)
}