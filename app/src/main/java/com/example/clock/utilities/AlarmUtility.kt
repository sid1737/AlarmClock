package com.example.clock.utilities

import java.text.SimpleDateFormat
import java.time.LocalTime
import java.util.Locale

object AlarmUtility {
    fun isValid24HourFormatTime(time: String): Boolean {
        if (time.isBlank()) {
            return false
        }
        val timeFormat = SimpleDateFormat("HH:mm", Locale.getDefault())
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
        return if (hours in 0..23) {
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

        val currentTimeInMinutes = currentTime.hour * 60 + currentTime.minute
        val targetTimeInMinutes = targetTime.hour * 60 + targetTime.minute

        var difference = targetTimeInMinutes - currentTimeInMinutes

        if (difference < 0) {
            difference += 24 * 60
        }

        val remainingHours = difference / 60
        val remainingMinutes = difference % 60

        return "Alarm in ${remainingHours}h ${remainingMinutes}mins"
    }
}