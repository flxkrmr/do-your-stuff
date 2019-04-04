package com.example.doyourstuff.data;

import org.threeten.bp.LocalDateTime;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ScheduledTimeMeasuring {
    private final LocalDateTime startTime;

    private final LiveData<Long> measuredTime = new MutableLiveData<>();

    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    private final Runnable measuringTask = new Runnable() {
        @Override
        public void run() {
            // TODO use JODA library to measure the difference and set measuredTime
        }
    };

    public ScheduledTimeMeasuring(LocalDateTime startTime, long publishInterval, TimeUnit timeUnit) {
        this.startTime = startTime;
        executor.schedule(measuringTask, publishInterval, timeUnit);
    }

    public void dispose() {
        executor.shutdown();
    }

    public LiveData<Long> getMeasuredTime() {
        return measuredTime;
    }
}
