package com.example.clock.ui.alarmScreen

import android.app.NotificationManager
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.clock.R
import com.example.clock.domain.repository.ALARM_NAME_KEY
import com.example.clock.domain.repository.AlarmNotificationManagerImpl
import com.example.clock.domain.repository.TIME_DATA_KEY
import com.example.clock.ui.common.components.CurvedTextButton
import com.example.clock.ui.theme.ClockTheme
import com.example.clock.ui.theme.brightBlue
import com.example.clock.ui.theme.defaultPadding
import com.example.clock.ui.theme.montSerratFontFamily

class AlarmScreenActivity : ComponentActivity() {
    private lateinit var alarmManager: AlarmNotificationManagerImpl

    @RequiresApi(Build.VERSION_CODES.O_MR1)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmManager = AlarmNotificationManagerImpl(applicationContext)
        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.O ||
            Build.VERSION.SDK_INT == Build.VERSION_CODES.P) {
            window.addFlags(
                WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON or
                        WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED or
                        WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON
            )
        }
        enableEdgeToEdge()
        alarmManager.startRingtone()
        val timeData = intent.getStringExtra(TIME_DATA_KEY) ?: "Alarm Triggered"
        val alarmName = intent?.getStringExtra(ALARM_NAME_KEY) ?: "Wake Up"

        setContent {
            ClockTheme {
                val context = LocalContext.current
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(defaultPadding),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Icon(
                            painter = painterResource(R.drawable.ic_alarm_clock_blue),
                            tint = brightBlue,
                            contentDescription = null,
                        )
                        Spacer(
                            modifier = Modifier.height(defaultPadding)
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                color = brightBlue,
                                fontFamily = montSerratFontFamily,
                                fontSize = 82.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ),
                            text = timeData
                        )
                        Spacer(
                            modifier = Modifier.height(defaultPadding)
                        )
                        Text(
                            modifier = Modifier.fillMaxWidth(),
                            style = TextStyle(
                                fontFamily = montSerratFontFamily,
                                fontSize = 24.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            ),
                            text = alarmName
                        )
                        Spacer(
                            modifier = Modifier.height(defaultPadding)
                        )
                        CurvedTextButton(
                            isEnabled = true,
                            buttonText = context.getString(R.string.turn_off_button_text)
                        ) {
                            finishAndRemoveTask()
                        }
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        alarmManager.endRingtone()
    }

    override fun onResume() {
        super.onResume()
        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.cancelAll()
    }
}