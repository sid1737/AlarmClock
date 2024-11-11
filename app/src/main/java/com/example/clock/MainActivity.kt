package com.example.clock

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
            ).build()
        }

        val alarmListViewModelFactory = AlarmListViewModelFactory(database)
        val alarmListViewModel = ViewModelProvider(this , alarmListViewModelFactory)[AlarmListViewModel::class.java]

        enableEdgeToEdge()
        setContent {
            ClockTheme {
                Surface(
                    modifier = Modifier.fillMaxSize()
                ) {
                    MainNavController(
                        alarmListViewModel = alarmListViewModel
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
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            MainNavController(alarmListViewModel = viewModel())
        }
    }
}