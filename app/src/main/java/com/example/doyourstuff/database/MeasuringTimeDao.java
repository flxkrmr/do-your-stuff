package com.example.doyourstuff.database;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
interface MeasuringTimeDao {
    @Query("SELECT * FROM measuringtime")
    LiveData<List<MeasuringTime>> getAll();

    @Insert
    void insert(MeasuringTime measuringTime);
}
