package com.example.clock.ui.navigation

sealed class Screen(val route: String) {
    data object AlarmListScreen: Screen("alarm_list_screen")
    data object CreateAlarmScreen: Screen("create_alarm_screen")
}