package com.example.doyourstuff.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {TimeMeasurement.class}, version = 1)
@TypeConverters({DataConverters.class})
public abstract class TimeMeasurementDatabase extends RoomDatabase {
    public abstract TimeMeasurementDao newStartTimeEventDao();

    private static volatile TimeMeasurementDatabase INSTANCE;

    public static TimeMeasurementDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (TimeMeasurementDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            TimeMeasurementDatabase.class, "time_measurement")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
