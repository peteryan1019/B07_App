package com.uoft.b07application.ui.admin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
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
import java.util.Locale;

public class AnnouncementDialog extends AppCompatDialogFragment {
    final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();
    private String senderUsername;
    private String senderEmail;

    private EditText messageBody;
    private AutoCompleteTextView recipientAutoTextView;
    private AutoCompleteTextView subjectAutoTextView;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.compose_announcement, null);


        builder.setView(view)
                .setNegativeButton("close", null)
                .setPositiveButton("send", null);
        messageBody = view.findViewById(R.id.messageBodyEditText);
        recipientAutoTextView = view.findViewById(R.id.recipientAutoCompleteTextView);
        subjectAutoTextView = view.findViewById(R.id.subjectAutoCompleteTextView);
        //does not work for now
//        ArrayAdapter<CharSequence> recipientAdapter = ArrayAdapter.createFromResource(requireContext(),
//                R.array.recipient_dropdown_items, android.R.layout.simple_dropdown_item_1line);
//        recipientAdapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
//        recipientAutoTextView.setAdapter(recipientAdapter);

        AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener(new DialogInterface.OnShowListener() {
            @Override
            public void onShow(DialogInterface dialog) {
                Button positiveButton = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
                positiveButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final String message = messageBody.getText().toString();
                        final String recipient = recipientAutoTextView.getText().toString();
                        final String subject = subjectAutoTextView.getText().toString();
                        if(message.isEmpty()||recipient.isEmpty()||subject.isEmpty()){
                            Toast.makeText(getContext(), "please fill out all fields", Toast.LENGTH_SHORT).show();

                        }
                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss", Locale.getDefault());
                        String currentDate = dateFormat.format(new Date());
                        String currentTime = timeFormat.format(new Date());
                        sendAnnouncement(message, recipient, subject, senderUsername, senderEmail, currentTime, currentDate);



                        Toast.makeText(getContext(), "announcement sent", Toast.LENGTH_SHORT).show();
                        dismiss();
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
    public EditText getMessageBody(){
        return messageBody;
    }

    public AutoCompleteTextView getRecipientAutoTextView() {
        return recipientAutoTextView;
    }
    public AutoCompleteTextView getSubjectAutoTextView(){
        return subjectAutoTextView;
    }

    public void setSenderUsername(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    private void sendAnnouncement(String message, String recipient, String subject, String senderUsername, String senderEmail, String time, String date) {


        HashMap announcement = new HashMap();
        DatabaseReference announcementRef = databaseReference.child("announcements").push();
        announcement.put("message", message);
        announcement.put("recipient", recipient);
        announcement.put("subject", subject);
        announcement.put("senderUsername", senderUsername);
        announcement.put("senderEmail", senderEmail);
        announcement.put("time", time);
        announcement.put("date", date);

        announcement.put("announcementKey", announcementRef.getKey());
        announcementRef.setValue(announcement);
        createReadAnnouncement(announcementRef.getKey());
    }
    private void createReadAnnouncement(String announcementKey) {
        databaseReference.child("users").child("admins").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    DatabaseReference readRef = databaseReference.child("reads").push();
                    readRef.child("announcementKey").setValue(announcementKey);
                    readRef.child("username").setValue(userSnapshot.child("username").getValue());
                    readRef.child("isRead").setValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "error to add read", Toast.LENGTH_SHORT);
            }
        });
        databaseReference.child("users").child("students").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot userSnapshot : dataSnapshot.getChildren()) {
                    DatabaseReference readRef = databaseReference.child("reads").push();
                    readRef.child("announcementKey").setValue(announcementKey);
                    readRef.child("username").setValue(userSnapshot.child("username").getValue());
                    readRef.child("isRead").setValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getContext(), "error to add read", Toast.LENGTH_SHORT);
            }
        });
    }
}



