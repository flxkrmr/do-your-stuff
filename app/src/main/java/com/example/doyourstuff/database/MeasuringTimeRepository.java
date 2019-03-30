package com.example.doyourstuff.database;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

class MeasuringTimeRepository {
    private final MeasuringTimeDao dao;

    private final LiveData<List<MeasuringTime>> allEntries;

    MeasuringTimeRepository(final Application application) {
        final MeasuringTimeDatabase database = MeasuringTimeDatabase.getDatabase(application);
        this.dao = database.newMeasuringTimeDao();
        this.allEntries = dao.getAll();
    }

    LiveData<List<MeasuringTime>> getAllEntries() {
        return allEntries;
    }

    void insert (MeasuringTime entry) {
        new insertAsyncTask(dao).execute(entry);
    }

    private static class insertAsyncTask extends AsyncTask<MeasuringTime, Void, Void> {

        private MeasuringTimeDao asyncTaskDao;

        insertAsyncTask(MeasuringTimeDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MeasuringTime... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
