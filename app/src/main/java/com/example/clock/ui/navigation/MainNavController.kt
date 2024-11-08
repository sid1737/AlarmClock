package com.example.clock.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clock.ui.alarmListScreen.AlarmListScreen

@Composable
fun MainNavController(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.AlarmListScreen.route
    ) {
        composable(route = Screen.AlarmListScreen.route) {
            AlarmListScreen()
        }
        composable(route = Screen.CreateAlarmScreen.route) {

        }
    }
}