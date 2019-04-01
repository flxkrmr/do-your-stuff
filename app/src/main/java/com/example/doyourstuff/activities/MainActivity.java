package com.example.doyourstuff.activities;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.doyourstuff.R;
import com.example.doyourstuff.data.ScheduledTimeMeasuring;
import com.example.doyourstuff.data.TimeMeasurement;
import com.example.doyourstuff.data.TimeMeasurementViewModel;
import com.jakewharton.threetenabp.AndroidThreeTen;

import java.util.List;


public class MainActivity extends AppCompatActivity {
    private static final String TIME_MEASURING_GUI_STATE = "timeMeasurementGuiState";
    private final MutableLiveData<Boolean> timeMeasurementGuiState = new MutableLiveData<>();
    private final MutableLiveData<Boolean> timeMeasurementGuiBlock = new MutableLiveData<>();
    private LiveData<TimeMeasurement> openTimeMeasurement;

    private TimeMeasurementViewModel timeMeasurementViewModel;

    private final class TimeMeasurementStateObserver implements Observer<Boolean> {
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

    private final class TimeMeasurementBlockObserver implements Observer<Boolean> {
        @Override
        public void onChanged(Boolean block) {
            findViewById(R.id.buttonStartStop).setEnabled(!block);
            findViewById(R.id.buttonStartStopAt).setEnabled(!block);
        }
    }

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        AndroidThreeTen.init(this);

        timeMeasurementGuiState.observe(this, new TimeMeasurementStateObserver());
        timeMeasurementGuiState.setValue(false);

        timeMeasurementGuiBlock.observe(this, new TimeMeasurementBlockObserver());
        timeMeasurementGuiBlock.setValue(false);

        RecyclerView recyclerView = findViewById(R.id.eventView);
        final EventViewAdapter adapter = new EventViewAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        timeMeasurementViewModel = ViewModelProviders.of(this).get(TimeMeasurementViewModel.class);
        timeMeasurementViewModel.getAllMeasurements().observe(this, new Observer<List<TimeMeasurement>>() {
            @Override
            public void onChanged(List<TimeMeasurement> events) {
                adapter.setTimeMeasurements(events);
            }
        });

        openTimeMeasurement = timeMeasurementViewModel.getOpenMeasurement();
        openTimeMeasurement.observe(this, new Observer<TimeMeasurement>() {
            @Override
            public void onChanged(TimeMeasurement measurement) {
                if(measurement == null) {
                    timeMeasurementGuiState.setValue(false);
                } else {
                    timeMeasurementGuiState.setValue(true);
                }
                timeMeasurementGuiBlock.setValue(false);
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
        //timeMeasurementGuiState.setValue(bundle.getBoolean(TIME_MEASURING_GUI_STATE, false));
    }

    @Override
    public void onSaveInstanceState(final Bundle bundle) {
        //putBoolean(TIME_MEASURING_GUI_STATE, getTimeMeasuringGui());
        super.onSaveInstanceState(bundle);
    }

    public void openProfileManagement(final MenuItem menuItem) {
        final Intent intent = new Intent(this, ProfileManagementActivity.class);
        startActivity(intent);
    }

    public void startStopTimeMeasurement(final View view) {
        toggleMeasurement();
    }

    //TODO
    public void startStopTimeMeasurementAt(final View view) {
        toggleMeasurement();
    }

    private void toggleMeasurement() {
        final Boolean guiBlocked = timeMeasurementGuiBlock.getValue();
        if(guiBlocked == null || guiBlocked)
            return;
        timeMeasurementGuiBlock.setValue(true);

        final TimeMeasurement currentMeasurement = openTimeMeasurement.getValue();
        if(currentMeasurement == null) {
            timeMeasurementViewModel.startMeasurement();
        } else {
            timeMeasurementViewModel.stopMeasurement(currentMeasurement);
        }

    }

}
