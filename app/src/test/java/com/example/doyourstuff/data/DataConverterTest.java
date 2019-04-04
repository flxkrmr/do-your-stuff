package com.example.doyourstuff.data;

import com.example.doyourstuff.BuildConfig;
import com.example.doyourstuff.TestApp;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.threeten.bp.LocalDateTime;
import org.threeten.bp.ZoneOffset;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.*;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21, application = TestApp.class)
public class DataConverterTest {


    @Test
    public void testTimestampToDate() {
        final Date testDate = new Date();
        final long testDateTimestamp = testDate.getTime();

        assertThat(DataConverters.timestampToDate(testDateTimestamp), is(testDate));
        assertThat(DataConverters.timestampToDate(null), nullValue());
    }

    @Test
    public void testDateToTimestamp() {
        final Date testDate = new Date();
        final long testDateTimestamp = testDate.getTime();

        assertThat(DataConverters.dateToTimestamp(testDate), is(testDateTimestamp));
        assertThat(DataConverters.dateToTimestamp(null), nullValue());
    }

    @Test
    public void testTimestampToLocalDateTime() {
        final LocalDateTime testDateTime = LocalDateTime.now().withNano(0);
        final long testDateTimeTimestamp = testDateTime.atZone(ZoneOffset.UTC).toEpochSecond();

        assertThat(DataConverters.timestampToLocalDateTime(testDateTimeTimestamp), is(testDateTime));
        assertThat(DataConverters.timestampToLocalDateTime(null), nullValue());
    }

    @Test
    public void testLocalDateTimeToTimestamp() {
        final LocalDateTime testDateTime = LocalDateTime.now();
        final long testDateTimeTimestamp = testDateTime.atZone(ZoneOffset.UTC).toEpochSecond();

        assertThat(DataConverters.localDateTimeToTimestamp(testDateTime), is(testDateTimeTimestamp));
        assertThat(DataConverters.localDateTimeToTimestamp(null), nullValue());
    }
}
