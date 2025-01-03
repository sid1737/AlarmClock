package com.example.clock.ui.alarmListScreen

import com.example.clock.domain.models.Alarm

sealed class AlarmListEvents {
    data class DisableAlarm(val alarm: Alarm): AlarmListEvents()
    data class DeleteAlarm(val alarm: Alarm): AlarmListEvents()
    data class EnableAlarm(val alarm: Alarm): AlarmListEvents()
}