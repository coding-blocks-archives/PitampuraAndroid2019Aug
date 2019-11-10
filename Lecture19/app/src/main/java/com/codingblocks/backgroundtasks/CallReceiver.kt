package com.codingblocks.backgroundtasks

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.Toast

class CallReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, p1: Intent) {
        Log.i("RECEIVED", "BROADCAST")
        if (p1.action == Intent.ACTION_AIRPLANE_MODE_CHANGED)
            Toast.makeText(context, "Broadcast Received", Toast.LENGTH_SHORT).show()
    }

}