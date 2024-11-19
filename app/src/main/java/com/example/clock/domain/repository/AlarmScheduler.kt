package com.example.clock.domain.repository

import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.annotation.RequiresApi
import com.example.clock.utilities.AlarmReceiver
import com.example.clock.utilities.AlarmUtility.convertTimeToMilliseconds

interface AlarmScheduler {
    fun setAlarm(timeInMilliseconds: String, pendingIntent: PendingIntent)
    fun getPendingIntent(): PendingIntent
    fun allowedToCreateAlarms(): Boolean
    fun navigateToPermissionsForAlarm()
}

class AlarmSchedulerImpl(
    private val context: Context
): AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    @SuppressLint("ScheduleExactAlarm")
    override fun setAlarm(timeInMilliseconds: String, pendingIntent: PendingIntent) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            convertTimeToMilliseconds(timeInMilliseconds),
            pendingIntent
        )
    }

    override fun getPendingIntent(): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java)
        return PendingIntent.getBroadcast(
            context,
            0,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun allowedToCreateAlarms(): Boolean {
        return alarmManager.canScheduleExactAlarms()
    }

    @RequiresApi(Build.VERSION_CODES.S)
    override fun navigateToPermissionsForAlarm() {
        Intent().also { intent ->
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.action = ACTION_REQUEST_SCHEDULE_EXACT_ALARM
            context.startActivity(intent)
        }
    }
}