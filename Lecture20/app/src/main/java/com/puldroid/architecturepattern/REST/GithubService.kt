package com.puldroid.architecturepattern.REST

import com.puldroid.architecturepattern.models.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubService {

    @GET("users/{username}")
    suspend fun getUser(@Path("username") name: String) : Response<User>

    @GET("users/{username}")
    suspend fun getPullkit(@Path("username") name: String) : Response<User>

    @GET("users/{username}")
    fun getUserTest(@Path("username") name: String) : Call<User>

}