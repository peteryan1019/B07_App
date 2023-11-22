package com.uoft.b07application;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class StudentComplaintPageActivity extends AppCompatActivity {

    Button submit_button;
    FirebaseDatabase database;
    private EditText editTextComplaint;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_complaint_page);

        submit_button = findViewById(R.id.submit);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("complaints");


        // Initialize UI elements
        editTextComplaint = findViewById(R.id.editTextComplaint);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                EditText complaint_topic = findViewById(R.id.complaint_topic);
//                String topic = complaint_topic.getText().toString().trim();
                String complaint_body = editTextComplaint.getText().toString().trim();
                if (!complaint_body.isEmpty()) {
                    // Generate a unique key for the complaint
                    String complaintId = reference.child("complaints").push().getKey();
                reference.child("complaints").child(complaintId).setValue(complaint_body)
                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                // Handle success
                                Toast.makeText(StudentComplaintPageActivity.this, "Complaint submitted", Toast.LENGTH_SHORT).show();
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                // Handle failure
                                Toast.makeText(StudentComplaintPageActivity.this, "Failed to submit complaint", Toast.LENGTH_SHORT).show();
                            }
                        });
            } else {
                editTextComplaint.setError("Please enter a complaint");
            }

            }
        });
    }

}