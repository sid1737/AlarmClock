package com.example.clock.utilities

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Locale

object AlarmUtility {
    fun isValid12HourFormatTime(time: String): Boolean {
        val timeFormat = SimpleDateFormat("hh:mm", Locale.getDefault())
        timeFormat.isLenient = false

        return try {
            timeFormat.parse(time) != null
        } catch (e: Exception) {
            false
        }
    }

    fun checkForSpecialCharacters(newHours: String): Boolean {
        val regex = ".*[,!@#.-].*".toRegex()
        return regex.containsMatchIn(newHours)
    }

    fun validateHourInput(newHours: String): String {
        if (newHours.isBlank()) {
            return newHours
        }
        val hours = newHours.toInt()
        return if (hours in 0..12) {
            newHours
        } else {
            newHours.substring(0,1)
        }
    }

    fun validateMinutesInput(newMinutes: String): String {
        if (newMinutes.isBlank()) {
            return newMinutes
        }
        val minutes = newMinutes.toInt()
        return if (minutes in 0..59) {
            return newMinutes
        } else {
            newMinutes.substring(0,1)
        }
    }

    fun calculateRemainingTime(hours: String, minutes: String): String {
        val targetTime = LocalTime.of(hours.toInt(), minutes.toInt())

        val currentTime = LocalTime.now()
        val currentTimeIn12HourFormat = currentTime.withHour(currentTime.hour % 12)

        val currentTimeInMinutes = currentTimeIn12HourFormat.hour * 60 + currentTimeIn12HourFormat.minute
        val targetTimeInMinutes = targetTime.hour * 60 + targetTime.minute

        var difference = targetTimeInMinutes - currentTimeInMinutes

        if (difference < 0) {
            difference += 12 * 60
        }

        val remainingHours = difference / 60
        val remainingMinutes = difference % 60

        return "Alarm in ${remainingHours}h ${remainingMinutes}mins"
    }
}