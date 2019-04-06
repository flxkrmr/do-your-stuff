package com.example.doyourstuff.data;

import com.example.doyourstuff.BuildConfig;
import com.example.doyourstuff.TestApp;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.threeten.bp.Duration;
import org.threeten.bp.LocalDateTime;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;


@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApp.class)
public class ScheduledTimeMeasuringTest {

    @Rule
    public InstantTaskExecutorRule instantTaskExecutorRule =
            new InstantTaskExecutorRule();

    @Test
    public void test() throws InterruptedException {
        final LocalDateTime startTime = LocalDateTime.now().minusMinutes(30);
        final Duration publishInterval = Duration.ofMillis(500);
        final ScheduledTimeMeasuring scheduler = new ScheduledTimeMeasuring(startTime, publishInterval);


        LiveData<Duration> measuredTime = scheduler.getMeasuredTime();

        measuredTime.observeForever(new Observer<Duration>() {
            @Override
            public void onChanged(Duration duration) {
                //System.out.println(duration);
            }
        });

        Thread.sleep(2000);
        scheduler.dispose();
        assertThat(scheduler.isShutdown(), is(true));
    }
}
