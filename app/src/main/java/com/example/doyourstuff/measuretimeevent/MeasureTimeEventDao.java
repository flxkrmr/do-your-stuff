package com.example.doyourstuff.measuretimeevent;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
interface MeasureTimeEventDao {
    @Query("SELECT * FROM MeasureTimeEvent")
    LiveData<List<MeasureTimeEvent>> getAll();

    @Insert
    void insert(MeasureTimeEvent measureTimeEvent);
}
