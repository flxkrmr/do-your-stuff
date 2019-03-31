package com.example.doyourstuff.data;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

class TimeMeasurementRepository {
    private final TimeMeasurementDao timeMeasurementDao;

    private final LiveData<List<TimeMeasurement>> allTimeMeasurements;
    private final LiveData<TimeMeasurement> openTimeMeasurement;

    TimeMeasurementRepository(final Application application) {
        final TimeMeasurementDatabase timeMeasurementDatabase = TimeMeasurementDatabase.getDatabase(application);
        this.timeMeasurementDao = timeMeasurementDatabase.newStartTimeEventDao();
        this.allTimeMeasurements = timeMeasurementDao.getAll();
        this.openTimeMeasurement = timeMeasurementDao.getOpenMeasurement();
    }

    LiveData<List<TimeMeasurement>> getAllTimeMeasurements() {
        return allTimeMeasurements;
    }

    LiveData<TimeMeasurement> getOpenTimeMeasurement() {
        return openTimeMeasurement;
    }

    void insertMeasurement (TimeMeasurement measurement) {
        new insertTimeMeasurementTask(timeMeasurementDao).execute(measurement);
    }

    void updateMeasurement (TimeMeasurement measurement) {
        new updateTimeMeasurementTask(timeMeasurementDao).execute(measurement);
    }

    private static class insertTimeMeasurementTask extends AsyncTask<TimeMeasurement, Void, Void> {

        private TimeMeasurementDao asyncTaskDao;

        insertTimeMeasurementTask(TimeMeasurementDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final TimeMeasurement... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }

    private static class updateTimeMeasurementTask extends AsyncTask<TimeMeasurement, Void, Void> {

        private TimeMeasurementDao asyncTaskDao;

        updateTimeMeasurementTask(TimeMeasurementDao dao) {
            this.asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(TimeMeasurement... params) {
            asyncTaskDao.update(params[0]);
            return null;
        }
    }
}
