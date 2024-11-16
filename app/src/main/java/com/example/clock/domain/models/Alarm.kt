package com.example.clock.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarms")
data class Alarm(
    @PrimaryKey
    val id: Int? = null,
    val time: String,
    var isAlarmActive: Boolean,
    val alarmDescription: String,
    val alarmName: String
)
