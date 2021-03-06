package com.example.doyourstuff.activities;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doyourstuff.R;
import com.example.doyourstuff.data.TimeMeasurement;

import org.threeten.bp.LocalDateTime;
import org.threeten.bp.format.DateTimeFormatter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

public class EventViewAdapter extends RecyclerView.Adapter<EventViewAdapter.EventViewHolder> {

    class EventViewHolder extends RecyclerView.ViewHolder {
        final TextView eventItemView;

        public EventViewHolder(@NonNull View itemView) {
            super(itemView);
            this.eventItemView = itemView.findViewById(R.id.eventItemTextView);
        }
    }


    private final LayoutInflater inflater;
    private List<TimeMeasurement> timeMeasurements;

    @SuppressLint("SimpleDateFormat")
    private String datesToString(LocalDateTime date1, @Nullable LocalDateTime date2) {
        final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        String output = formatter.format(date1);
        if(date2 == null)
            return output;

        output += "  -  ";
        output += formatter.format(date2);

        return output;
    }

    public EventViewAdapter(final Context context) {
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View itemView = inflater.inflate(R.layout.eventview_item, parent, false);
        return new EventViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull EventViewHolder holder, int position) {
        if (timeMeasurements != null) {
            TimeMeasurement current = timeMeasurements.get(position);
            holder.eventItemView.setText(datesToString(current.getStartDate(), current.getStopDate()));
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("Nothing here");
        }
    }

    public void setTimeMeasurements(List<TimeMeasurement> timeMeasurements) {
        this.timeMeasurements = timeMeasurements;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return timeMeasurements != null ? timeMeasurements.size() : 0;
    }


}
