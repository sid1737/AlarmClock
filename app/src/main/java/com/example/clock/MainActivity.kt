package com.example.clock

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.clock.ui.alarmList.components.AlarmCard
import com.example.clock.ui.theme.ClockTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ClockTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize()
                ) { innerPadding ->
                    AlarmCard(
                        modifier = Modifier.padding(innerPadding),
                        alarmTitle = "Wake up now",
                        alarmTime = "10:00",
                        alarmDescription = "Alarm in 30 mins",
                        isDayTime = true
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClockTheme {
        Scaffold(
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AlarmCard(
                modifier = Modifier.padding(innerPadding),
                alarmTitle = "Wake up now",
                alarmTime = "10:00AM",
                alarmDescription = "Alarm in 30 mins",
                isDayTime = true
            )
        }
    }
}