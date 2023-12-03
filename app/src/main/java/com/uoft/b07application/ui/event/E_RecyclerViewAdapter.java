package com.uoft.b07application.ui.event;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Button;

import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
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
    String username;

    public E_RecyclerViewAdapter(Context context, ArrayList<EventModel> events, boolean visibility, String commenterName, String commenterEmail, String username) {
        this.context = context;
        this.events = events;
        this.viewFeedBackButtonVisibility = visibility;
        this.commenterEmail = commenterEmail;
        this.commenterName = commenterName;
        this.databaseReference = FirebaseDatabase.getInstance().getReference();
        this.username = username;
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
        holder.eventNameView.setText(String.format("Event: %s", events.get(position).getEventName()));
        holder.eventDateView.setText(String.format("Date: %s", events.get(position).getEventDate()));
        holder.eventKey.setText(events.get(position).getKey());
        if (viewFeedBackButtonVisibility) holder.setViewButtonVisibility(View.VISIBLE);
        else holder.setViewButtonVisibility(View.INVISIBLE);
        holder.commentFeedbackButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, StudentFeedback.class);
                intent.putExtra("eventName", holder.eventNameView.getText().
                        toString().replaceFirst("Event: ", ""));
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
                // handleSignup inside checkSignUP
                checkSignUp(eventKey, username);
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
        // Get signup ref
        DatabaseReference signUpEventRef = databaseReference.child("signups");

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
//                        databaseReference.child("signups").child(signUpKey[0]).child("isSignUpEvent").setValue(true);
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

    private void checkSignUp(String eventKey, String targetUsername) {
        DatabaseReference signupsRef = FirebaseDatabase.getInstance().getReference("signups");
        final boolean[] isSignUp = {false};
        final String[] signupKey = new String[1];
        signupsRef.orderByChild("eventKey").equalTo(eventKey).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


                // Iterate through the results to find the matching username
                for (DataSnapshot signupSnapshot : dataSnapshot.getChildren()) {
                    String username = (String) signupSnapshot.child("username").getValue();

                    if (username != null && username.equals(targetUsername)) {
                        // Found the matching child
                        isSignUp[0] = (boolean) signupSnapshot.child("isSignUpEvent").getValue();
                        signupKey[0] = signupSnapshot.getKey();
                        if(!isSignUp[0]){
                            databaseReference.child("signups").child(signupSnapshot.getKey()).child("isSignUpEvent").setValue(true);
                            handleSignup(eventKey);
                        }
                        else Toast.makeText(context, "Already sign up", Toast.LENGTH_SHORT).show();
                        break; // No need to continue iterating
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(context, "Cannot check is signup", Toast.LENGTH_SHORT).show();
            }
        });
    }



    public static class E_ViewHolder extends RecyclerView.ViewHolder {
        TextView eventNameView;
        TextView eventDateView;
        TextView eventKey;
        ImageButton viewFeedbackButton;
        ImageButton commentFeedbackButton;

        Button signupButton; // Add this line


        public E_ViewHolder(@NonNull View itemView) {
            super(itemView);
            eventKey = itemView.findViewById(R.id.event_key);
            eventNameView = itemView.findViewById(R.id.event_name);
            eventDateView = itemView.findViewById(R.id.event_date);
            viewFeedbackButton = itemView.findViewById(R.id.view_feedback_button);
            commentFeedbackButton = itemView.findViewById(R.id.comment_feedback_button);
            signupButton = itemView.findViewById(R.id.signup_button);
            signupButton.setText("Sign Up"); // Set the text to "Signup"
        }

        public void setViewButtonVisibility(int visibility) {
            viewFeedbackButton.setVisibility(visibility);
        }
    }
}
