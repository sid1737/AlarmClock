package com.example.clock.domain.repository

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.Ringtone
import android.media.RingtoneManager
import androidx.core.app.NotificationCompat
import com.example.clock.R
import com.example.clock.utilities.NOTIFICATION_CHANNEL_ID
import com.example.clock.utilities.NOTIFICATION_CHANNEL_NAME
import kotlin.random.Random

interface AlarmNotificationManager {
    fun showNotificationWithAlarm(alarmIntent: Intent, timeData: String, alarmName: String)
    fun startRingtone()
    fun endRingtone()
}

class AlarmNotificationManagerImpl (private val context: Context): AlarmNotificationManager {

    private var ringtone: Ringtone? = null

    override fun showNotificationWithAlarm(
        alarmIntent: Intent,
        timeData: String,
        alarmName: String
    ) {
        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            alarmIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        context.startActivity(alarmIntent)

        val notificationChannel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_HIGH
        )

        val notificationManager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(notificationChannel)
        val builder = NotificationCompat
            .Builder(context, NOTIFICATION_CHANNEL_ID)
            .setContentTitle(alarmName)
            .setContentText("Alarm triggered at $timeData")
            .setChannelId(NOTIFICATION_CHANNEL_ID)
            .setSmallIcon(R.drawable.ic_clock)
            .setFullScreenIntent(pendingIntent, true)
        val notificationObject = builder.build()

        notificationManager.notify(Random.nextInt(), notificationObject)
    }

    override fun startRingtone() {
        val alarmUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_ALARM)
        ringtone = RingtoneManager.getRingtone(context, alarmUri)
        ringtone?.play()
    }

    override fun endRingtone() {
        ringtone?.stop()
    }
}