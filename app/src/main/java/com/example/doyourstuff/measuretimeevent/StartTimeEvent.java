package com.example.doyourstuff.measuretimeevent;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.PrimaryKey;

public class StartTimeEvent {
    @PrimaryKey(autoGenerate = true)
    private int uid;

    @NonNull
    @ColumnInfo(name = "date")
    private String date;
}
