package com.mohammed.mosa.eg.dailylabor.util

import android.Manifest
import android.content.ContentValues
import android.content.Context
import android.content.pm.PackageManager
import android.provider.CalendarContract
import android.widget.Toast
import androidx.core.content.ContextCompat
import java.util.Date
import java.util.TimeZone

class CalendarEventScheduler(private val context: Context) {
    companion object {
        private const val CALENDAR_PERMISSION = Manifest.permission.WRITE_CALENDAR
    }

    fun hasCalendarPermission(): Boolean {
        return ContextCompat.checkSelfPermission(
            context,
            CALENDAR_PERMISSION
        ) == PackageManager.PERMISSION_GRANTED
    }

    fun addEventToCalendar(
        title: String,
        description: String,
        location: String,
        startTime: Date,
        endTime: Date,
        isAllDay: Boolean = false,
        reminder: Int = 5 // Minutes before event
    ): Long? {
        if (!hasCalendarPermission()) {
            Toast.makeText(context, " ERR", Toast.LENGTH_LONG).show()
            return null
        }

        try {
            val calendarId = getDefaultCalendarId() ?: return null

            val values = ContentValues().apply {
                put(CalendarContract.Events.CALENDAR_ID, calendarId)
                put(CalendarContract.Events.TITLE, title)
                put(CalendarContract.Events.DESCRIPTION, description)
                put(CalendarContract.Events.EVENT_LOCATION, location)

                val startMillis = startTime.time
                val endMillis = endTime.time

                put(CalendarContract.Events.DTSTART, startMillis)
                put(CalendarContract.Events.DTEND, endMillis)
                put(CalendarContract.Events.ALL_DAY, if (isAllDay) 1 else 0)

                put(CalendarContract.Events.EVENT_TIMEZONE, TimeZone.getDefault().id)
            }

            val uri = context.contentResolver.insert(CalendarContract.Events.CONTENT_URI, values)
            val eventId = uri?.lastPathSegment?.toLong()

            // Add reminder if specified
            eventId?.let { id ->
                addEventReminder(id, reminder)
            }

            return eventId
        } catch (e: SecurityException) {
            e.printStackTrace()
            return null
        }
    }

    private fun addEventReminder(eventId: Long, minutes: Int) {
        val values = ContentValues().apply {
            put(CalendarContract.Reminders.EVENT_ID, eventId)
            put(CalendarContract.Reminders.METHOD, CalendarContract.Reminders.METHOD_ALERT)
            put(CalendarContract.Reminders.MINUTES, minutes)
        }

        context.contentResolver.insert(CalendarContract.Reminders.CONTENT_URI, values)
    }

    private fun getDefaultCalendarId(): Long? {
        val projection = arrayOf(
            CalendarContract.Calendars._ID,
            CalendarContract.Calendars.CALENDAR_DISPLAY_NAME
        )

        val uri = CalendarContract.Calendars.CONTENT_URI
        val cursor = context.contentResolver.query(uri, projection, null, null, null)

        return cursor?.use {
            if (it.moveToFirst()) {
                it.getLong(0)
            } else null
        }
    }
}
