package com.example.clock.ui.createAlarmScreen

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clock.data.database.AlarmDatabase
import com.example.clock.data.repository.AlarmRepositoryImpl
import com.example.clock.domain.models.Alarm
import com.example.clock.domain.repository.AlarmRepository
import com.example.clock.domain.repository.AlarmScheduler
import com.example.clock.domain.repository.AlarmSchedulerImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CreateAlarmViewModel(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
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

    fun scheduleAlarm(timeData: String) {
        val pendingIntent = alarmScheduler.getPendingIntent()
        alarmScheduler.setAlarm(timeData, pendingIntent)
    }
}

class CreateAlarmViewModelFactory (private val database: AlarmDatabase, private  val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CreateAlarmViewModel::class.java)) {
            val repositoryImpl = AlarmRepositoryImpl(database.alarmDao)
            val permissionManager = AlarmSchedulerImpl(context)
            return CreateAlarmViewModel(repositoryImpl, permissionManager) as T
        }
        throw IllegalArgumentException("Unknown Viewmodel class")
    }
}