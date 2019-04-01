package com.example.doyourstuff.data;

import org.threeten.bp.Instant;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.Date;

import androidx.room.TypeConverter;

public class DataConverters {
    @TypeConverter
    public static Date timestampToDate(Long value) {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date) {
        return date == null ? null : date.getTime();
    }

    @TypeConverter
    public static LocalDateTime timestampToLocalDateTime(Long value) {
        return value == null ? null : LocalDateTime.ofInstant(Instant.ofEpochSecond(value), ZoneOffset.UTC);
    }

    @TypeConverter
    public static Long localDateTimeToTimestamp(LocalDateTime value) {
        return value == null ? null : value.atZone(ZoneOffset.UTC).toEpochSecond();
    }
}
