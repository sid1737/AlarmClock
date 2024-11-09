package com.example.clock.ui.alarmListScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.clock.data.database.AlarmDatabase
import com.example.clock.data.repository.AlarmRepositoryImpl
import com.example.clock.domain.repository.AlarmRepository

class AlarmListViewModel(private val alarmRepository: AlarmRepository): ViewModel() {

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