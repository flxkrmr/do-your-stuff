package com.example.doyourstuff.data;

import org.threeten.bp.Duration;
import org.threeten.bp.LocalDateTime;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

public class ScheduledTimeMeasuring {

    private final MutableLiveData<Duration> measuredTimeObservable = new MutableLiveData<>();

    private final ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(2);

    public ScheduledTimeMeasuring(final LocalDateTime startTime, Duration publishInterval) {
        final Runnable measuringTask = new Runnable() {
            @Override
            public void run() {
                final Duration measuredTime = Duration.between(startTime, LocalDateTime.now());
                measuredTimeObservable.postValue(measuredTime);
            }
        };

        executor.scheduleAtFixedRate(measuringTask, 0, publishInterval.toNanos(), TimeUnit.NANOSECONDS);
    }

    public void dispose() {
        executor.shutdown();
    }

    boolean isShutdown() {
        return executor.isShutdown();
    }

    public LiveData<Duration> getMeasuredTime() {
        return measuredTimeObservable;
    }
}
