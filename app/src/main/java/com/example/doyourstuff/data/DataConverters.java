package com.example.doyourstuff.data;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.Date;

import androidx.room.TypeConverter;

class DataConverters {
    @TypeConverter
    static Date timestampToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    static LocalDateTime timestampToLocalDateTime(Long value) {
        return value == null ? null : LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneOffset.UTC);
    }

    @TypeConverter
    static Long localDateTimeToTimestamp(LocalDateTime value) {
        return value == null ? null : value.atZone(ZoneOffset.UTC).toEpochSecond();
    }
}
