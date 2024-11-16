package com.example.clock.ui.createAlarmScreen

import com.example.clock.domain.models.Alarm

sealed class CreateAlarmEvents {
    data class OnSaveButtonClick(val alarm: Alarm): CreateAlarmEvents()
}
