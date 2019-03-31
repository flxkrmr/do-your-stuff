package com.example.doyourstuff.data;

import android.app.Application;

import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

public class TimeMeasurementViewModel extends AndroidViewModel {
    private final TimeMeasurementRepository repository;
    private final LiveData<List<TimeMeasurement>> allMeasurements;

    public TimeMeasurementViewModel(@NonNull Application application) {
        super(application);
        repository = new TimeMeasurementRepository(application);
        allMeasurements = repository.getAllTimeMeasurements();
    }

    public LiveData<List<TimeMeasurement>> getAllMeasurements() {
        return allMeasurements;
    }

    public void startMeasurement() {
        final TimeMeasurement measurement = new TimeMeasurement(0, new Date(), null);
        repository.insertMeasurement(measurement);
    }

    public LiveData<TimeMeasurement> getOpenMeasurement() {
        return repository.getOpenTimeMeasurement();
    }

    public void stopMeasurement(final TimeMeasurement measurement) {
        final TimeMeasurement measurementWithStopTime =
                new TimeMeasurement(measurement.getUid(), measurement.getStartDate(), new Date());
        repository.updateMeasurement(measurementWithStopTime);
    }
}
