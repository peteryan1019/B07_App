package com.uoft.b07application.ui.event;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.student.StudentFeedback;

import java.util.ArrayList;

public class E_RecyclerViewAdapter extends RecyclerView.Adapter<E_RecyclerViewAdapter.E_ViewHolder> {
    Context context;
    ArrayList<EventModel> events = new ArrayList<>();

    boolean viewFeedBackButtonVisibility;

    public E_RecyclerViewAdapter(Context context, ArrayList<EventModel> events, boolean visibility) {
        this.context = context;
        this.events = events;
        this.viewFeedBackButtonVisibility = visibility;
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
        holder.eventNameView.setText(String.format("Event name: %s", events.get(position).getEventName()));
        holder.eventDateView.setText(String.format("Date: %s", events.get(position).getEventDate()));
        holder.eventKey.setText(events.get(position).getKey());
        if (viewFeedBackButtonVisibility) holder.setViewButtonVisibility(View.VISIBLE);
        else holder.setViewButtonVisibility(View.INVISIBLE);
        holder.commentFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Build your intent here and start the activity or perform the desired action
                // For example, you can open a new activity using an intent
                Intent intent = new Intent(context, StudentFeedback.class);
                intent.putExtra("eventName", holder.eventNameView.getText().
                        toString().replaceFirst("Event name: ", ""));
                intent.putExtra("eventData", holder.eventDateView.getText().toString()
                        .replaceFirst("Date: ", ""));
                intent.putExtra("eventKey", holder.eventKey.getText().toString());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }


    public static class E_ViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameView;
        TextView eventDateView;
        TextView eventKey;
        ImageButton viewFeedbackButton;
        ImageButton commentFeedbackButton;

        public E_ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventKey = itemView.findViewById(R.id.event_key);
            eventNameView = itemView.findViewById(R.id.event_name);
            eventDateView = itemView.findViewById(R.id.event_date);
            viewFeedbackButton = itemView.findViewById(R.id.view_feedback_button);
            commentFeedbackButton = itemView.findViewById(R.id.comment_feedback_button);

        }

        public void setViewButtonVisibility(int visibility) {
            viewFeedbackButton.setVisibility(visibility);
        }
    }
}
