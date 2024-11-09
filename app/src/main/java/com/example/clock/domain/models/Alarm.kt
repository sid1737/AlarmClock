package com.example.clock.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alarm_table")
data class Alarm(
    @PrimaryKey
    val id: Int? = null,
    val alarmTitle: String,
    val time: String,
    val timeDesignation: String,
    var isAlarmActive: Boolean
)
