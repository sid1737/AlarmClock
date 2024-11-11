package com.example.clock.data.repository

import com.example.clock.data.database.AlarmDao
import com.example.clock.domain.models.Alarm
import com.example.clock.domain.repository.AlarmRepository
import kotlinx.coroutines.flow.Flow

class AlarmRepositoryImpl(
    private val alarmDao: AlarmDao
): AlarmRepository {

    override fun getAlarms(): Flow<List<Alarm>> {
        return alarmDao.getAlarms()
    }

    override suspend fun insertAlarm(alarm: Alarm) {
        alarmDao.insertAlarm(alarm)
    }

    override suspend fun updateAlarm(alarm: Alarm) {
        alarmDao.updateAlarm(alarm)
    }
}