package com.example.doyourstuff.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.doyourstuff.R;
import com.example.doyourstuff.database.MeasuringTime;
import com.example.doyourstuff.database.MeasuringTimeViewModel;

import java.util.Date;
import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String STATE_MEASURING_TIME = "measuringTime";
    private final MutableLiveData<Boolean> measuringTime = new MutableLiveData<>();
    private MeasuringTimeViewModel viewModel;

    private final class MeasuringTimeStateObserver implements Observer<Boolean> {
        @Override
        public void onChanged(Boolean measuring) {
            final Button startStopButton = findViewById(R.id.buttonStartStop);
            final Button startStopButtonAt = findViewById(R.id.buttonStartStopAt);
            if(measuring) {
                startStopButton.setText(R.string.button_time_stop);
                startStopButtonAt.setText(R.string.button_time_stop_at);
            } else {
                startStopButton.setText(R.string.button_time_start);
                startStopButtonAt.setText(R.string.button_time_start_at);
            }
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        measuringTime.observe(this, new MeasuringTimeStateObserver());
        initMeasuringTime();

        RecyclerView recyclerView = findViewById(R.id.eventView);
        final EventViewAdapter adapter = new EventViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewModel = ViewModelProviders.of(this).get(MeasuringTimeViewModel.class);
        viewModel.getAllEntries().observe(this, new Observer<List<MeasuringTime>>() {
            @Override
            public void onChanged(List<MeasuringTime> events) {
                adapter.setEvents(events);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public void onRestoreInstanceState(final Bundle bundle) {
        measuringTime.setValue(bundle.getBoolean(STATE_MEASURING_TIME, false));
    }

    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        bundle.putBoolean(STATE_MEASURING_TIME, getMeasuringTimeOrFalse());
        super.onSaveInstanceState(bundle);
    }

    public void openProfileManagement(final MenuItem menuItem) {
        final Intent intent = new Intent(this, ProfileManagementActivity.class);
        startActivity(intent);
    }

    public void startStopTimeMeasurement(final View view) {
        final boolean currentlyMeasuring = getMeasuringTimeOrFalse();

        if(currentlyMeasuring)
            viewModel.insert(MeasuringTime.stopEvent(new Date()));
        else
            viewModel.insert(MeasuringTime.startEvent(new Date()));

        toggleMeasuringTime();
    }

    public void startStopTimeMeasurementAt(final View view) {
        toggleMeasuringTime();
    }

    private void toggleMeasuringTime() {
        measuringTime.setValue(!getMeasuringTimeOrFalse());
    }

    private void initMeasuringTime() {
        measuringTime.setValue(false);
    }

    private boolean getMeasuringTimeOrFalse() {
        final Boolean currentState = measuringTime.getValue();
        return currentState != null ? currentState : false;
    }
}
