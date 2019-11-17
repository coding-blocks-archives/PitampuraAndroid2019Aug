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
    private var userRepository: UserRepository = UserRepository.getInstance()
    var userData: MutableLiveData<User> = MutableLiveData()
    var userListData: MutableLiveData<User> = MutableLiveData()


    init {
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