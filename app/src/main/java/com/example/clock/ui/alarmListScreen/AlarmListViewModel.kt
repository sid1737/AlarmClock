package com.example.clock.ui.alarmListScreen

import android.content.Context
import android.os.Build
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
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AlarmListViewModel(
    private val alarmRepository: AlarmRepository,
    private val alarmScheduler: AlarmScheduler
): ViewModel() {

    private val _listOfAlarmData = MutableStateFlow<List<Alarm>>(mutableListOf())
    val listOfAlarmData = _listOfAlarmData.asStateFlow()

    private var alarmsJob: Job? = null

    init {
        alarmsJob?.cancel()
        alarmsJob = viewModelScope.launch {
            alarmRepository.getAlarms().map { listOfAlarms ->
                _listOfAlarmData.emit(listOfAlarms)
            }.collect()
        }
    }

    fun onEvent(event: AlarmListEvents) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is AlarmListEvents.DisableAlarm -> {
                    alarmRepository.updateAlarm(event.alarm)
                    alarmScheduler.cancelAlarm(
                        timeData = event.alarm.time,
                        alarmName = event.alarm.alarmName,
                        requestCode = event.alarm.pendingIntentRequestCode
                    )
                }
                is AlarmListEvents.EnableAlarm -> {
                    alarmRepository.updateAlarm(event.alarm)
                    val pendingIntent = alarmScheduler.getPendingIntentWithData(
                        event.alarm.time,
                        event.alarm.alarmName,
                        event.alarm.pendingIntentRequestCode
                    )
                    alarmScheduler.setAlarm(event.alarm.time, pendingIntent)
                }
                is AlarmListEvents.DeleteAlarm -> alarmRepository.deleteAlarm(event.alarm)
            }
        }
    }

    fun isAndroid12AndAboveAndScheduleAlarm(): Boolean {
        return  if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            alarmScheduler.allowedToCreateAlarms()
        } else {
            true
        }
    }

    fun navigateToGetPermissionsForAlarm() {
        alarmScheduler.navigateToPermissionsForAlarm()
    }

    fun navigateToGetPermissionForOverlay() {
        alarmScheduler.navigateToPermissionForOverlay()
    }

    fun canAppDrawOverlays(): Boolean {
        return alarmScheduler.canDrawOverlay()
    }
}

class AlarmListViewModelFactory(private val database: AlarmDatabase, private val context: Context): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmListViewModel::class.java)) {
            val repositoryImpl = AlarmRepositoryImpl(database.alarmDao)
            val permissionManager = AlarmSchedulerImpl(context)
            return AlarmListViewModel(repositoryImpl, permissionManager) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}