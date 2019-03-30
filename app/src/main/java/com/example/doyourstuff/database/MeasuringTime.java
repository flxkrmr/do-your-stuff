package com.example.doyourstuff.database;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MeasuringTime {
    public MeasuringTime(final int uid, @NonNull final String date, final boolean isStartEvent) {
        this.uid = uid;
        this.date = date;
        this.isStartEvent = isStartEvent;
    }

    private MeasuringTime(final Date date, final boolean isStartEvent) {
        this.date = date.toString();
        this.isStartEvent = isStartEvent;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;

    @ColumnInfo(name = "is_start_event")
    private boolean isStartEvent;

    public int getUid() {
        return uid;
    }

    public String getDate() {
        return date;
    }

    public boolean isStartEvent() {
        return isStartEvent;
    }

    public static MeasuringTime stopEvent(final Date date) {
        return new MeasuringTime(date, false);
    }

    public static MeasuringTime startEvent(final Date date) {
        return new MeasuringTime(date, true);
    }
}
