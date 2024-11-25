package com.example.clock

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.room.Room
import com.example.clock.data.database.AlarmDatabase
import com.example.clock.ui.alarmListScreen.AlarmListViewModel
import com.example.clock.ui.alarmListScreen.AlarmListViewModelFactory
import com.example.clock.ui.createAlarmScreen.CreateAlarmViewModel
import com.example.clock.ui.createAlarmScreen.CreateAlarmViewModelFactory
import com.example.clock.ui.navigation.MainNavController
import com.example.clock.ui.theme.ClockTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        val database by lazy {
            Room.databaseBuilder(
                applicationContext,
                AlarmDatabase::class.java,
                "alarm_database"
            ).fallbackToDestructiveMigration()
                .build()
        }

        val alarmListViewModelFactory = AlarmListViewModelFactory(database, applicationContext)
        val alarmListViewModel = ViewModelProvider(this , alarmListViewModelFactory)[AlarmListViewModel::class.java]

        val createAlarmViewModelFactory = CreateAlarmViewModelFactory(database, applicationContext)
        val createAlarmViewModel = ViewModelProvider(this, createAlarmViewModelFactory)[CreateAlarmViewModel::class.java]

        enableEdgeToEdge()
        checkPushNotificationPermission()
        setContent {
            ClockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainNavController(
                        alarmListViewModel = alarmListViewModel,
                        createAlarmViewModel = createAlarmViewModel
                    )
                }
            }
        }
    }

    private fun checkPushNotificationPermission() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            val isPushNotificationPermissionGranted = checkSelfPermission(Manifest.permission.POST_NOTIFICATIONS)
            if (isPushNotificationPermissionGranted != PackageManager.PERMISSION_GRANTED) {
                requestPermissions(
                    arrayOf( Manifest.permission.POST_NOTIFICATIONS),
                    1
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    ClockTheme {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavController(
                alarmListViewModel = viewModel(),
                createAlarmViewModel = viewModel()
            )
        }
    }
}