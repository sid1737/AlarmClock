package com.example.clock.domain.repository

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.provider.Settings
import android.provider.Settings.ACTION_MANAGE_OVERLAY_PERMISSION
import android.provider.Settings.ACTION_REQUEST_SCHEDULE_EXACT_ALARM
import androidx.annotation.RequiresApi
import com.example.clock.utilities.AlarmReceiver
import com.example.clock.utilities.AlarmUtility.convertTimeToMilliseconds

const val TIME_DATA_KEY = "time_data"
const val ALARM_NAME_KEY = "alarm_name"

interface AlarmScheduler {
    fun setAlarm(timeInMilliseconds: String, pendingIntent: PendingIntent)
    fun getPendingIntentWithData(timeData: String, alarmName: String, pendingIntentRequestCode: Int): PendingIntent
    fun allowedToCreateAlarms(): Boolean
    fun navigateToPermissionsForAlarm()
    fun navigateToPermissionForOverlay()
    fun canDrawOverlay(): Boolean
    fun cancelAlarm(requestCode: Int, timeData: String, alarmName: String)
}

class AlarmSchedulerImpl(
    private val context: Context
): AlarmScheduler {

    private val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

    override fun setAlarm(timeInMilliseconds: String, pendingIntent: PendingIntent) {
        alarmManager.setExactAndAllowWhileIdle(
            AlarmManager.RTC_WAKEUP,
            convertTimeToMilliseconds(timeInMilliseconds),
            pendingIntent
        )
    }

    override fun getPendingIntentWithData(
        timeData: String,
        alarmName: String,
        pendingIntentRequestCode: Int
    ): PendingIntent {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(TIME_DATA_KEY, timeData)
            putExtra(ALARM_NAME_KEY, alarmName)
        }
        return PendingIntent.getBroadcast(
            context,
            pendingIntentRequestCode,
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

    override fun navigateToPermissionForOverlay() {
        Intent().also { intent ->
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
            intent.action = ACTION_MANAGE_OVERLAY_PERMISSION
            context.startActivity(intent)
        }
    }

    override fun canDrawOverlay(): Boolean {
        return Settings.canDrawOverlays(context)
    }

    override fun cancelAlarm(requestCode: Int, timeData: String, alarmName: String) {
        val intent = Intent(context, AlarmReceiver::class.java).apply {
            putExtra(TIME_DATA_KEY, timeData)
            putExtra(ALARM_NAME_KEY, alarmName)
        }
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            requestCode,
            intent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )
        alarmManager.cancel(pendingIntent)
    }
}