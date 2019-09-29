package com.codingblocks.retrofit

import retrofit2.Call
import retrofit2.http.GET

interface GithubService {

    @GET("users/aggarwalpulkit596")
    fun getUser() : Call<String>

}