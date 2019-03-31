package com.example.doyourstuff.data;

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
    private Date startDate;

    @ColumnInfo(name = "stop_date")
    private Date stopDate;

    public TimeMeasurement(int uid, @NonNull Date startDate, Date stopDate) {
        this.uid = uid;
        this.startDate = startDate;
        this.stopDate = stopDate;
    }

    public int getUid() {
        return uid;
    }

    @NonNull
    public Date getStartDate() {
        return startDate;
    }

    public Date getStopDate() {
        return stopDate;
    }

}
