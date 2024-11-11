package com.example.clock.ui.alarmListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.clock.data.database.AlarmDatabase
import com.example.clock.data.repository.AlarmRepositoryImpl
import com.example.clock.domain.models.Alarm
import com.example.clock.domain.repository.AlarmRepository
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class AlarmListViewModel(
    private val alarmRepository: AlarmRepository
): ViewModel() {

    private val _listOfAlarmData = MutableStateFlow<List<Alarm>>(emptyList())
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
        viewModelScope.launch {
            if (event is AlarmListEvents.DisableAlarm) {
                alarmRepository.updateAlarm(event.alarm)
            }
        }
    }
}

class AlarmListViewModelFactory(private val database: AlarmDatabase): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AlarmListViewModel::class.java)) {
            val repositoryImpl = AlarmRepositoryImpl(database.alarmDao)
            return AlarmListViewModel(repositoryImpl) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}