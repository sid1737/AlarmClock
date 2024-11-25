package com.example.clock.ui.alarmScreen

import android.app.NotificationManager
import android.content.Context
import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.ui.text.font.FontStyle
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        alarmManager = AlarmNotificationManagerImpl(applicationContext)
        // Make the activity appear as an overlay
        window.setFlags(
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON,
            WindowManager.LayoutParams.FLAG_NOT_TOUCH_MODAL or
                    WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        )
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
                                fontFamily = montSerratFontFamily,
                                fontSize = 82.sp,
                                fontWeight = FontWeight.Bold,
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
                                fontWeight = FontWeight.Normal,
                                fontStyle = FontStyle.Normal,
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