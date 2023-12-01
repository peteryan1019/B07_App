package com.uoft.b07application.ui.event;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import com.uoft.b07application.ui.event.EventModel;
import com.uoft.b07application.ui.event.EventDialog;
import com.google.firebase.database.DataSnapshot;
import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.admin.FeedbackBottomSheetFragment;
import com.uoft.b07application.ui.student.StudentFeedback;

import java.util.ArrayList;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class E_RecyclerViewAdapter extends RecyclerView.Adapter<E_RecyclerViewAdapter.E_ViewHolder> {
    Context context;
    ArrayList<EventModel> events = new ArrayList<>();

    boolean viewFeedBackButtonVisibility;
    String commenterName;
    String commenterEmail;

    DatabaseReference databaseReference;

    public E_RecyclerViewAdapter(Context context, ArrayList<EventModel> events, boolean visibility, String commenterName, String commenterEmail) {
        this.context = context;
        this.events = events;
        this.viewFeedBackButtonVisibility = visibility;
        this.commenterEmail = commenterEmail;
        this.commenterName = commenterName;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
    }

    //for checking if already signed
    private boolean hasStudentSignedUp(String eventKey, String studentEmail) {
        DatabaseReference eventsRef = databaseReference.child("events");
        DatabaseReference signupsRef = eventsRef.child(eventKey).child("signups").child(studentEmail);

        return signupsRef != null;
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
        int itemPosition = position;
        holder.eventNameView.setText(String.format("Event name: %s", events.get(position).getEventName()));
        holder.eventDateView.setText(String.format("Date: %s", events.get(position).getEventDate()));
        holder.eventKey.setText(events.get(position).getKey());
        if (viewFeedBackButtonVisibility) holder.setViewButtonVisibility(View.VISIBLE);
        else holder.setViewButtonVisibility(View.INVISIBLE);
        holder.commentFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentFeedback.class);
                intent.putExtra("eventName", holder.eventNameView.getText().
                        toString().replaceFirst("Event name: ", ""));
                intent.putExtra("eventData", holder.eventDateView.getText().toString()
                        .replaceFirst("Date: ", ""));
                intent.putExtra("eventKey", holder.eventKey.getText().toString());
                intent.putExtra("commenterName", commenterName);
                intent.putExtra("commenterEmail", commenterEmail);
                context.startActivity(intent);
            }
        });
        holder.viewFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FeedbackBottomSheetFragment bottomSheetFragment =
                        new FeedbackBottomSheetFragment(holder.eventNameView.getText().toString()
                                , holder.eventKey.getText().toString());
                bottomSheetFragment.show(((FragmentActivity) context)
                        .getSupportFragmentManager(), bottomSheetFragment.getTag());

            }
        });

        holder.signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eventKey = events.get(itemPosition).getKey();
                handleSignup(eventKey);
            }
        });

    }

    @Override
    public int getItemCount() {
        return events.size();
    }

    private void handleSignup(String eventKey) {
        // Get a reference to the events node in Firebase
        DatabaseReference eventsRef = databaseReference.child("events");

        // Get the current number of attendees for the specific event
        DatabaseReference numAttendeesRef = eventsRef.child(eventKey).child("numAttendees");
        numAttendeesRef.get().addOnCompleteListener(task -> {
            if (task.isSuccessful()) {
                DataSnapshot snapshot = task.getResult();
                if (snapshot.exists()) {
                    // Get the current number of attendees
                    int numAttendees = Integer.parseInt(snapshot.getValue().toString());

                    // Check if there are available slots
                    if (numAttendees > 0) {
                        // Subtract one from numAttendees and update Firebase
                        eventsRef.child(eventKey).child("numAttendees").setValue(numAttendees - 1);

                        // Display a success message
                        Toast.makeText(context, "Signed up for the event", Toast.LENGTH_SHORT).show();
                    } else {
                        // Display a message indicating the event is full
                        Toast.makeText(context, "Event is full", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

}




    public static class E_ViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameView;
        TextView eventDateView;
        TextView eventKey;
        ImageButton viewFeedbackButton;
        ImageButton commentFeedbackButton;

        ImageButton signupButton; // Add this line


        public E_ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventKey = itemView.findViewById(R.id.event_key);
            eventNameView = itemView.findViewById(R.id.event_name);
            eventDateView = itemView.findViewById(R.id.event_date);
            viewFeedbackButton = itemView.findViewById(R.id.view_feedback_button);
            commentFeedbackButton = itemView.findViewById(R.id.comment_feedback_button);
            signupButton = itemView.findViewById(R.id.signup_button);
        }

        public void setViewButtonVisibility(int visibility) {
            viewFeedbackButton.setVisibility(visibility);
        }
    }
}
