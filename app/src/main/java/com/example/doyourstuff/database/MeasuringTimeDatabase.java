package com.example.doyourstuff.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MeasuringTime.class}, version = 1)
abstract class MeasuringTimeDatabase extends RoomDatabase {
    abstract MeasuringTimeDao newMeasuringTimeDao();

    private static volatile MeasuringTimeDatabase INSTANCE;

    public static MeasuringTimeDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MeasuringTimeDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MeasuringTimeDatabase.class, "measuringtime")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
