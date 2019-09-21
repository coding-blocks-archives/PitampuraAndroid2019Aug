package com.codingblocks.networkingokhttp


data class User(
	val gistsUrl: String,
	val reposUrl: String,
	val followingUrl: String,
	val receivedEventsUrl: String,
	val followers: Int,
	val avatarUrl: String
	)