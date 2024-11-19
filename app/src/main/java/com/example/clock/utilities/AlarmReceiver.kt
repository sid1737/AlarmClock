package com.example.clock.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d("Time", "Current Time is ${System.currentTimeMillis()}")
        val received = intent?.action
    }
}