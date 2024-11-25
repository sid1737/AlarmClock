package com.example.clock.utilities

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.Build
import com.example.clock.domain.repository.ALARM_NAME_KEY
import com.example.clock.domain.repository.AlarmNotificationManagerImpl
import com.example.clock.domain.repository.TIME_DATA_KEY
import com.example.clock.ui.alarmScreen.AlarmScreenActivity

const val NOTIFICATION_CHANNEL_ID = "general_communication_id"
const val NOTIFICATION_CHANNEL_NAME = "General Communication"

class AlarmReceiver: BroadcastReceiver() {

    override fun onReceive(context: Context?, intent: Intent?) {
        val timeData = intent?.getStringExtra(TIME_DATA_KEY) ?: "Alarm Triggered"
        val alarmName = intent?.getStringExtra(ALARM_NAME_KEY) ?: "Wake Up"

        val alarmIntent = Intent(context, AlarmScreenActivity::class.java).apply {
            putExtra(TIME_DATA_KEY, timeData)
            putExtra(ALARM_NAME_KEY, alarmName)
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
        }

        if (Build.VERSION.SDK_INT <= Build.VERSION_CODES.Q) {
            context?.startActivity(alarmIntent)
        } else {
            context?.let {
                val alarmManagerImpl = AlarmNotificationManagerImpl(it)
                alarmManagerImpl.showNotificationWithAlarm(
                    alarmIntent,
                    timeData,
                    alarmName
                )
            }
        }
    }
}