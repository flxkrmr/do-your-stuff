package com.example.doyourstuff.measuretimeevent;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MeasureTimeEvent.class}, version = 1)
abstract class MeasureTimeEventDatabase extends RoomDatabase {
    abstract MeasureTimeEventDao newMeasuringTimeDao();

    private static volatile MeasureTimeEventDatabase INSTANCE;

    public static MeasureTimeEventDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (MeasureTimeEventDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            MeasureTimeEventDatabase.class, "measuringtime")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
