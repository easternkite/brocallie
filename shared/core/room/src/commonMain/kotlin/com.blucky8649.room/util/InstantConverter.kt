package com.blucky8649.room.util

import androidx.room.TypeConverter
import kotlinx.datetime.Instant

internal class InstantConverter {
    @TypeConverter
    fun longToInstant(value: Long?): Instant? =
        value?.let { Instant.fromEpochMilliseconds(it) }

    @TypeConverter
    fun instantToLong(instant: Instant?): Long? =
        instant?.toEpochMilliseconds()
}