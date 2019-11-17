package com.puldroid.architecturepattern

import com.puldroid.architecturepattern.REST.Client
import org.junit.Assert.assertNotNull
import org.junit.Test

class ApiTest {


    @Test
    fun checkUser() {
        val user = Client.service.getUserTest("aggarwalpulkit596").execute()
        assertNotNull(user.body())
    }
}