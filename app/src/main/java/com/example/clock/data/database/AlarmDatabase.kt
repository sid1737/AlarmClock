package com.example.clock.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.clock.domain.models.Alarm

@Database(
    entities = [Alarm::class],
    version = 1,
    exportSchema = false
)
abstract class AlarmDatabase: RoomDatabase() {

    abstract val alarmDao: AlarmDao
}