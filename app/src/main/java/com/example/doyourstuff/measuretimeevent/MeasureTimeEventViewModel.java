package com.example.doyourstuff.measuretimeevent;

import android.app.Application;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class MeasureTimeEventViewModel extends AndroidViewModel {
    private final MeasureTimeEventRepository repository;
    private final LiveData<List<MeasureTimeEvent>> allEntries;

    public MeasureTimeEventViewModel(@NonNull Application application) {
        super(application);
        repository = new MeasureTimeEventRepository(application);
        allEntries = repository.getAllEntries();
    }

    public LiveData<List<MeasureTimeEvent>> getAllEntries() {
        return allEntries;
    }

    public void insert(final MeasureTimeEvent measureTimeEvent) {
        repository.insert(measureTimeEvent);
    }
}
