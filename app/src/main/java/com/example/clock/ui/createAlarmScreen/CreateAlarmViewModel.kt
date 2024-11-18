package com.example.clock.ui.createAlarmScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clock.data.database.AlarmDatabase
import com.example.clock.data.repository.AlarmRepositoryImpl
import com.example.clock.domain.models.Alarm
import com.example.clock.domain.repository.AlarmRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAlarmViewModel(
    private val alarmRepository: AlarmRepository
): ViewModel() {

    fun onEvent(events: CreateAlarmEvents) {
        viewModelScope.launch(Dispatchers.IO) {
            if (events is CreateAlarmEvents.OnSaveButtonClick) {
                alarmRepository.insertAlarm(events.alarm)
            }
        }
    }

    fun createAlarmObject(
        time: String,
        isAlarmActive: Boolean = true,
        alarmDescription: String,
        alarmName: String
    ): Alarm {
        return Alarm(
            time = time,
            isAlarmActive = isAlarmActive,
            alarmDescription = alarmDescription,
            alarmName = alarmName
        )
    }
}

class CreateAlarmViewModelFactory (private val database: AlarmDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAlarmViewModel::class.java)) {
            val repositoryImpl = AlarmRepositoryImpl(database.alarmDao)
            return CreateAlarmViewModel(repositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}