package com.example.doyourstuff.activities;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.doyourstuff.R;
import com.example.doyourstuff.database.MeasuringTime;

import java.util.List;

import androidx.annotation.NonNull;
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
    private List<MeasuringTime> events;

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
        if (events != null) {
            MeasuringTime current = events.get(position);
            holder.eventItemView.setText(current.getDate());
        } else {
            // Covers the case of data not being ready yet.
            holder.eventItemView.setText("Nothing here");
        }
    }

    public void setEvents(List<MeasuringTime> events) {
        this.events = events;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return events != null ? events.size() : 0;
    }


}
