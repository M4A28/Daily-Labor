package com.mohammed.mosa.eg.dailylabor.data.converter


import androidx.room.TypeConverter
import java.time.LocalDateTime
import java.time.ZoneOffset

class LocalDateTimeConverter {

    @TypeConverter
    fun fromLocalDateTime(dateTime: LocalDateTime?): Long {
        return dateTime?.toEpochSecond(ZoneOffset.UTC)?: 0L
    }

    @TypeConverter
    fun toLocalDateTime(epochSeconds: Long?): LocalDateTime? {
        return epochSeconds?.let {
            LocalDateTime.ofEpochSecond(it, 0, ZoneOffset.UTC)
        }
    }
}