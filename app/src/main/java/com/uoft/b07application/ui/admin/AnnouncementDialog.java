package com.uoft.b07application.ui.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uoft.b07application.R;

import java.util.HashMap;

public class AnnouncementDialog extends AppCompatDialogFragment {
    DatabaseReference database;
    DatabaseReference reference;

    private EditText messageBody;
    private AutoCompleteTextView recipientAutoTextView;
    private AutoCompleteTextView subjectAutoTextView;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.compose_announcement, null);
        database = FirebaseDatabase.getInstance().getReference();


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
                        if(!(message.isEmpty()||recipient.isEmpty()||subject.isEmpty())){
                            sendAnnouncement(message, recipient, subject);
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
    public EditText getMessageBody(){
        return messageBody;
    }

    public AutoCompleteTextView getRecipientAutoTextView() {
        return recipientAutoTextView;
    }
    public AutoCompleteTextView getSubjectAutoTextView(){
        return subjectAutoTextView;
    }
    private void sendAnnouncement(String message, String recipient, String subject) {
        HashMap announcement = new HashMap();
        announcement.put("message", message);
        announcement.put("recipient", recipient);
        announcement.put("subject", subject);
        database.child("announcements").push().setValue(announcement);
    }
}



