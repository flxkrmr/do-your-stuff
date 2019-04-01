package com.example.doyourstuff.data;

import org.threeten.bp.LocalDateTime;

import java.util.Date;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "time_measurement")
public class TimeMeasurement {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @NonNull
    @ColumnInfo(name = "start_date")
    private LocalDateTime startDate;

    @ColumnInfo(name = "stop_date")
    private LocalDateTime stopDate;

    public TimeMeasurement(int uid, @NonNull LocalDateTime startDate, LocalDateTime stopDate) {
        this.uid = uid;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public int getUid() {
        return uid;
    }

    @NonNull
    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getStopDate() {
        return stopDate;
    }

}
