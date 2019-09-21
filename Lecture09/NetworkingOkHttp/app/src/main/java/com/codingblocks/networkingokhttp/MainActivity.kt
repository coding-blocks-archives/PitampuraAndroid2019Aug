package com.codingblocks.networkingokhttp

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val okHttpClient = OkHttpClient()

        val request = Request.Builder()
            .url("https://api.github.com/users/aggarwalpulkit596")
            .build()

        okHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.e("NETWORK", e.localizedMessage.toString())
            }

            override fun onResponse(call: Call, response: Response) {
                val result = response.body?.string()
                val gson =
                    GsonBuilder()
                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                        .create()
//                val jsonData = JSONObject(body)
                val user = gson.fromJson(result, User::class.java)
                Log.i("NETWORK", user.toString())

//                val user = User(
//                    avatar_url = jsonData.getString("avatar_url"),
//                    id = jsonData.getInt("id"),
//                    type = jsonData.getString("type"),
//                    login = jsonData.getString("login")
//                )


            }

        })
    }
}
