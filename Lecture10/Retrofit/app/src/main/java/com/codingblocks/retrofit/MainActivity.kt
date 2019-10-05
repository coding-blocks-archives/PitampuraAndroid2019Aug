package com.codingblocks.retrofit

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rvUsers.layoutManager = LinearLayoutManager(this)


//        Client.api.getAllUsers().enqueue(object : Callback<List<User>> {
//            override fun onFailure(call: Call<List<User>>, t: Throwable) {
//            }
//
//            override fun onResponse(call: Call<List<User>>, response: Response<List<User>>) {
//                val adapter = UsersAdapter(response.body() as ArrayList<User>)
//                rvUsers.adapter = adapter
//            }
//
//        })
        Client.api.getAllUsers()
            .enqueue(retrofitCallback {
                    throwable, response ->
                val adapter = UsersAdapter(response?.body() as ArrayList<User>)
                rvUsers.adapter = adapter
            })

    }

    override fun onStart() {
        super.onStart()

        Client.api.getUser("uditjain-cyber").enqueue(object : Callback<User> {
            override fun onFailure(call: Call<User>, t: Throwable) {
                TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()
                runOnUiThread {
                    textView.text = user?.name
                    textView2.text = user?.login
                    Picasso.get().load(user?.avatar_url).into(imageView)

                }

            }

        })
    }
}

data class User(
    val name: String,
    val login: String,
    val gistsUrl: String,
    val reposUrl: String,
    val followingUrl: String,
    val receivedEventsUrl: String,
    val followers: Int,
    val avatar_url: String
)
