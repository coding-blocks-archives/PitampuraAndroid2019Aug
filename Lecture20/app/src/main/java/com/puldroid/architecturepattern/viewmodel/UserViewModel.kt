package com.puldroid.architecturepattern.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.puldroid.architecturepattern.models.User
import com.puldroid.architecturepattern.repos.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class UserViewModel : ViewModel() {
    private lateinit var userRepository: UserRepository
    var userData: MutableLiveData<User> = MutableLiveData()
    var userListData: MutableLiveData<List<User>> = MutableLiveData()


    init {
        userRepository = UserRepository.getInstance()
        getUser()

    }

    private fun getUser() {
        GlobalScope.launch {
            val response = viewModelScope.async { userRepository.getUser() }
            val user = response.await()
            if (user.isSuccessful) {

                userListData.postValue(user.body())
            }
        }

    }
}