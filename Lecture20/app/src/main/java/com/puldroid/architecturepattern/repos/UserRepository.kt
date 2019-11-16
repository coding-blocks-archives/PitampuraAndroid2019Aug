package com.puldroid.architecturepattern.repos

import com.puldroid.architecturepattern.REST.Client

class UserRepository {
    /**
     *Non Coroutines Method
     **/
//    fun getUser(): MutableLiveData<User> {
//        val userData: MutableLiveData<User> = MutableLiveData()
//        Client.service.getUser("aggarwalpulkit596").enqueue(object : Callback<User> {
//            override fun onFailure(call: Call<User>, t: Throwable) {
//            }
//
//            override fun onResponse(call: Call<User>, response: Response<User>) {
//                userData.value =response.body()
//            }
//
//        })
//
//        return userData
//    }

    suspend fun getUser() = Client.service.getListUser("aggarwalpulkit596")

    companion object {
        private var userRepository = UserRepository()
        fun getInstance(): UserRepository {
            return userRepository
        }
    }
}