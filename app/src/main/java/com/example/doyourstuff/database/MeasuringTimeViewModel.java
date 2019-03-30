package com.example.doyourstuff.database;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MeasuringTimeViewModel extends AndroidViewModel {
    private final MeasuringTimeRepository repository;
    private final LiveData<List<MeasuringTime>> allEntries;

    public MeasuringTimeViewModel(@NonNull Application application) {
        super(application);
        repository = new MeasuringTimeRepository(application);
        allEntries = repository.getAllEntries();
    }

    public LiveData<List<MeasuringTime>> getAllEntries() {
        return allEntries;
    }

    public void insert(final MeasuringTime measuringTime) {
        repository.insert(measuringTime);
    }
}
