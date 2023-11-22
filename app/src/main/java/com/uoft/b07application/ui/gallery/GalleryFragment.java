package com.uoft.b07application.ui.gallery;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uoft.b07application.R;

public class GalleryFragment extends Fragment {

    private TextView editTextComplaint;
    private Button submitButton;
    private FirebaseDatabase database;
    private DatabaseReference reference;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);

        // Initialize Firebase components
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("complaints");

        // Initialize UI elements
        editTextComplaint = root.findViewById(R.id.editTextComplaint);
        submitButton = root.findViewById(R.id.submit_complaint);


        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String complaintBody = editTextComplaint.getText().toString().trim();
                EditText topic= root.findViewById(R.id.complaint_topic);
                String complaintTopic= topic.getText().toString();

                if (!complaintBody.isEmpty()) {
                    // Generate a unique key for the complaint
//                    String complaintId = reference.push().getKey(); // Corrected
                    String complaintId=complaintTopic;
                    if (complaintId != null) {
                        reference.child(complaintId).setValue(complaintBody)
                                .addOnSuccessListener(aVoid -> Toast.makeText(getActivity(), "Complaint submitted successfully!", Toast.LENGTH_SHORT).show())
                                .addOnFailureListener(e -> Toast.makeText(getActivity(), "Failed to submit complaint.", Toast.LENGTH_SHORT).show());
                    }
                } else {
                    editTextComplaint.setError("Please enter a complaint");
                }
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // Clear references to avoid memory leaks
        editTextComplaint = null;
        submitButton = null;
    }
}
