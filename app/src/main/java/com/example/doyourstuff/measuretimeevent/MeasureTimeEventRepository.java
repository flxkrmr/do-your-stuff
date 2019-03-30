package com.example.doyourstuff.measuretimeevent;

import android.app.Application;
import android.os.AsyncTask;

import java.util.List;

import androidx.lifecycle.LiveData;

class MeasureTimeEventRepository {
    private final MeasureTimeEventDao dao;

    private final LiveData<List<MeasureTimeEvent>> allEntries;

    MeasureTimeEventRepository(final Application application) {
        final MeasureTimeEventDatabase database = MeasureTimeEventDatabase.getDatabase(application);
        this.dao = database.newMeasuringTimeDao();
        this.allEntries = dao.getAll();
    }

    LiveData<List<MeasureTimeEvent>> getAllEntries() {
        return allEntries;
    }

    void insert (MeasureTimeEvent entry) {
        new insertAsyncTask(dao).execute(entry);
    }

    private static class insertAsyncTask extends AsyncTask<MeasureTimeEvent, Void, Void> {

        private MeasureTimeEventDao asyncTaskDao;

        insertAsyncTask(MeasureTimeEventDao dao) {
            asyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final MeasureTimeEvent... params) {
            asyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
