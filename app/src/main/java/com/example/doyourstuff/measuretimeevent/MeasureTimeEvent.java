package com.example.doyourstuff.measuretimeevent;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class MeasureTimeEvent {
    public MeasureTimeEvent(final int uid, @NonNull final Date date, final boolean isStartEvent) {
        this.uid = uid;
        this.date = date;
        this.isStartEvent = isStartEvent;
    }

    private MeasureTimeEvent(final Date date, final boolean isStartEvent) {
        this.date = date;
        this.isStartEvent = isStartEvent;
    }

    @PrimaryKey(autoGenerate = true)
    private int uid;

    @NonNull
    @ColumnInfo(name = "date")
    private Date date;

    @ColumnInfo(name = "is_start_event")
    private boolean isStartEvent;

    public int getUid() {
        return uid;
    }

    public Date getDate() {
        return date;
    }

    public boolean isStartEvent() {
        return isStartEvent;
    }

    public static MeasureTimeEvent stopEvent(final Date date) {
        return new MeasureTimeEvent(date, false);
    }

    public static MeasureTimeEvent startEvent(final Date date) {
        return new MeasureTimeEvent(date, true);
    }
}
