package com.uoft.b07application.ui.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.R;

import java.util.ArrayList;

public class E_RecyclerViewAdapter extends RecyclerView.Adapter<E_RecyclerViewAdapter.E_ViewHolder>{
    Context context;
    ArrayList<EventModel> events = new ArrayList<>();
    public E_RecyclerViewAdapter(Context context, ArrayList<EventModel> events){
        this.context = context;
        this.events = events;
    }

    @NonNull
    @Override
    public E_RecyclerViewAdapter.E_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.admin_event_recycler_row, parent, false);

        return new E_RecyclerViewAdapter.E_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull E_ViewHolder holder, int position) {
        holder.eventNameView.setText(events.get(position).getEventName());
        holder.eventDateView.setText(events.get(position).getEventDate());
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
    public static class E_ViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameView;
        TextView eventDateView;
        ImageButton viewFeedbackButton;

        public E_ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventNameView = itemView.findViewById(R.id.event_name);
            eventDateView = itemView.findViewById(R.id.event_date);
            viewFeedbackButton = itemView.findViewById(R.id.view_feedback_button);
        }
    }
}
