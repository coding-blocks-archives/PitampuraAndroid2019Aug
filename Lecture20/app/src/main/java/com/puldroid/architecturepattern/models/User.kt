package com.puldroid.architecturepattern.models

data class User(
    var login: String,
    val id: Int,
    val avatar_url: String,
    val type: String
)