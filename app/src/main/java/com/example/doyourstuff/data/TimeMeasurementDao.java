package com.example.doyourstuff.data;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface TimeMeasurementDao {

    @Query("SELECT * FROM time_measurement")
    LiveData<List<TimeMeasurement>> getAll();

    @Query("SELECT * FROM time_measurement WHERE stop_date IS NULL LIMIT 1")
    LiveData<TimeMeasurement> getOpenMeasurement();

    @Insert
    void insert(TimeMeasurement event);

    @Update
    void update(TimeMeasurement event);
}
