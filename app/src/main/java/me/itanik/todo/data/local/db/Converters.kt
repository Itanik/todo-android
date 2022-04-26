package me.itanik.todo.data.local.db

import androidx.room.TypeConverter
import java.util.*

class Converters {
    @TypeConverter
    fun dateFromTimestamp(value: Long?): Date? = value?.let { Date(it) }

    @TypeConverter
    fun dateToTimestamp(date: Date?): Long? = date?.time

    @TypeConverter
    fun uuidFromString(value: String): UUID = UUID.fromString(value)

    @TypeConverter
    fun uuidToString(uuid: UUID): String = uuid.toString()
}