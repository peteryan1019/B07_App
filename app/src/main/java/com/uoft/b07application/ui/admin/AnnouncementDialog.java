package com.uoft.b07application.ui.admin;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AutoCompleteTextView;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatDialogFragment;

import com.uoft.b07application.R;

public class AnnouncementDialog extends AppCompatDialogFragment {
    private EditText messageBody;
//    private ImageButton closeButton;
//    private ImageButton sendButton;
    private AutoCompleteTextView recipientAutoTextView;
    private AutoCompleteTextView subjectAutoTextView;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.compose_announcement, null);
        builder.setView(view)
                .setNegativeButton("close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle close button click
                    }
                })
                .setPositiveButton("send", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Handle send button click
                    }
                });
        messageBody = view.findViewById(R.id.messageBodyEditText);
        recipientAutoTextView = view.findViewById(R.id.recipientAutoCompleteTextView);
        subjectAutoTextView = view.findViewById(R.id.subjectAutoCompleteTextView);
        return builder.create();
    }


}



