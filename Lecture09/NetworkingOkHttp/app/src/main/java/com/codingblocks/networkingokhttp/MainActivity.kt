package com.codingblocks.networkingokhttp

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main//To change initializer of created properties use File | Settings | File Templates.

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        launch {
            val newUser = getUser()
            Log.i("NetWORK", newUser.toString())
            Toast.makeText(this@MainActivity,newUser.toString(),Toast.LENGTH_SHORT).show()

        }

    }

    suspend fun getUser(): User {

        var user = User(
            "",
            "",
            "",
            "",
            -1,
            ""
        )
        withContext(Dispatchers.IO) {

            val okHttpClient = OkHttpClient()

            val request = Request.Builder()
                .url("https://api.github.com/users/aggarwalpulkit596")
                .build()

            val result = okHttpClient.newCall(request).execute().body?.string()
            val gson =
                GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .create()
//                val jsonData = JSONObject(body)
            user = gson.fromJson(result, User::class.java)
        }
        return user


//        var user = User(
//            "",
//            "",
//            "",
//            "",
//            -1,
//            ""
//        )
//
//
//        okHttpClient.newCall(request).enqueue(object : Callback {
//            override fun onFailure(call: Call, e: IOException) {
//                Log.e("NETWORK", e.localizedMessage.toString())
//            }
//
//            override fun onResponse(call: Call, response: Response) {
//                val result = response.body?.string()
//                val gson =
//                    GsonBuilder()
//                        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
//                        .create()
////                val jsonData = JSONObject(body)
//                user = gson.fromJson(result, User::class.java)
//                Log.i("NETWORK", user.toString())
//
////                val user = User(
////                    avatar_url = jsonData.getString("avatar_url"),
////                    id = jsonData.getInt("id"),
////                    type = jsonData.getString("type"),
////                    login = jsonData.getString("login")
////                )
//
//
//            }
//
//        })

//        return user
    }

}
