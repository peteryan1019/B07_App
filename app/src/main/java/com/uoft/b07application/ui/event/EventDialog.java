package com.uoft.b07application.ui.event;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;

import java.util.HashMap;
import java.util.List;

public class EventDialog extends AppCompatDialogFragment {
    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    EditText eventName;
    EditText eventDate;
    EditText numAttendees;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.schedule_event, null);
        eventName = view.findViewById(R.id.event_name);
        eventDate = view.findViewById(R.id.event_date);
        numAttendees = view.findViewById(R.id.num_attendees); // Initialize the EditText for num attendees
        builder.setView(view)
                .setNegativeButton("close", null)
                .setPositiveButton("send", null);
        AlertDialog alertDialog = builder.create();
        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String eventNameString = eventName.getText().toString();
                        final String eventDateString = eventDate.getText().toString();
                        final String numAttendeesString = numAttendees.getText().toString();

                        if (eventNameString.isEmpty() || numAttendeesString.isEmpty()) {
                            Toast.makeText(getContext(), "Event name and number of attendees cannot be empty", Toast.LENGTH_SHORT).show();
                        } else {
                            scheduleEvent(eventNameString, eventDateString, numAttendeesString);
                            Toast.makeText(getContext(), "Event Scheduled",Toast.LENGTH_SHORT).show();
                            dismiss();
                        }
                    }
                });

                Button negativeButton = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
                negativeButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dismiss();
                    }
                });
            }
        });
        return alertDialog;
    }


   private void scheduleEvent(String eventName, String eventDate, String numAttendees) {
       int attendees = Integer.parseInt(numAttendees);
       DatabaseReference eventsRef = databaseReference.child("events");
       DatabaseReference newEventRef = eventsRef.push();

       // Set the basic event information
       newEventRef.child("eventName").setValue(eventName);
       newEventRef.child("eventDate").setValue(eventDate);
       newEventRef.child("numAttendees").setValue(attendees);

       // Create boolean between all users and all events
       createSignUpEvents(newEventRef.getKey());
   }

   private void createSignUpEvents(String eventKey) {
       databaseReference.child("users").child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                   DatabaseReference signUpRef = databaseReference.child("signups").push();
                   signUpRef.child("eventKey").setValue(eventKey);
                   signUpRef.child("username").setValue(userSnapshot.child("username").getValue());
                   signUpRef.child("isSignUpEvent").setValue(false);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(getContext(), "error to add sign up", Toast.LENGTH_SHORT);
           }
       });
       databaseReference.child("users").child("students").addListenerForSingleValueEvent(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                   DatabaseReference signUpRef = databaseReference.child("signups").push();
                   signUpRef.child("eventKey").setValue(eventKey);
                   signUpRef.child("username").setValue(userSnapshot.child("username").getValue());
                   signUpRef.child("isSignUpEvent").setValue(false);
               }
           }

           @Override
           public void onCancelled(@NonNull DatabaseError databaseError) {
               Toast.makeText(getContext(), "error to add sign up", Toast.LENGTH_SHORT);
           }
       });
   }

}
