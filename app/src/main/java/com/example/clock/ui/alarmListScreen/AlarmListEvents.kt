package com.example.clock.ui.alarmListScreen

import com.example.clock.domain.models.Alarm

sealed class AlarmListEvents {
    data class DisableAlarm(val alarm: Alarm): AlarmListEvents()
}