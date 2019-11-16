package com.puldroid.architecturepattern

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.puldroid.architecturepattern.viewmodel.UserViewModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val viewModel by lazy {
        ViewModelProviders.of(this).get(UserViewModel::class.java)
    }

//    companion object {
//        var name: String = ""
//        var photo: String = ""
//        var id: Int = 0
//    }

//    val userLiveData: LiveData<User> = MutableLiveData()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.userData.observe(this, Observer {
            Picasso.get().load(it.avatar_url).into(imageView)
            textView.text = it.login
            textView2.text = it.id.toString()
        })

//        if (savedInstanceState == null)
//            Client.service.getUser("aggarwalpulkit596").enqueue(object : Callback<User> {
//                override fun onFailure(call: Call<User>, t: Throwable) {
//                }
//
//                override fun onResponse(call: Call<User>, response: Response<User>) {
//                    if (response.isSuccessful) {
//                        runOnUiThread {
//                            name = response.body()?.login ?: ""
//                            photo = response.body()?.avatar_url ?: ""
//                            id = response.body()?.id ?: -1
//                            setUser()
//
//                        }
//                    }
//                }
//
//            })
//        else {
//            setUser()
//        }
    }

//    private fun setUser() {
//        Picasso.get().load(photo).into(imageView)
//        textView.text = name
//        textView2.text = id.toString()
//    }
}
