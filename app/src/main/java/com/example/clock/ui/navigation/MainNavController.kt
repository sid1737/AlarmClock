package com.example.clock.ui.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.clock.ui.alarmListScreen.AlarmListScreen
import com.example.clock.ui.alarmListScreen.AlarmListViewModel
import com.example.clock.ui.createAlarmScreen.CreateAlarmScreen
import com.example.clock.ui.createAlarmScreen.CreateAlarmViewModel

@Composable
fun MainNavController(
    modifier: Modifier = Modifier,
    alarmListViewModel: AlarmListViewModel,
    createAlarmViewModel: CreateAlarmViewModel
) {
    val navController = rememberNavController()

    NavHost(
        modifier = modifier.fillMaxSize(),
        navController = navController,
        startDestination = Screen.AlarmListScreen.route
    ) {
        composable(route = Screen.AlarmListScreen.route) {
            AlarmListScreen(
                navController = navController,
                viewModel = alarmListViewModel
            )
        }

        composable(route = Screen.CreateAlarmScreen.route) {
            CreateAlarmScreen(
                navController = navController,
                viewModel = createAlarmViewModel
            )
        }
    }
}