package com.example.kursovik.Core.Utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class DateUtils {
    companion object {
        fun getDateString(date: Date): String {
            return SimpleDateFormat("MMM dd yyyy").format(date)
        }

        fun isDateInCurrentWeek(date: Date?): Boolean {
            val currentCalendar: Calendar = Calendar.getInstance()
            val week: Int = currentCalendar.get(Calendar.WEEK_OF_YEAR)
            val year: Int = currentCalendar.get(Calendar.YEAR)
            val targetCalendar: Calendar = Calendar.getInstance()
            targetCalendar.setTime(date)
            val targetWeek: Int = targetCalendar.get(Calendar.WEEK_OF_YEAR)
            val targetYear: Int = targetCalendar.get(Calendar.YEAR)
            return week == targetWeek && year == targetYear
        }
    }

}