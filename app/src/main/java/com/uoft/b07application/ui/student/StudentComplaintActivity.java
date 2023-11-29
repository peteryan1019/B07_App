package com.uoft.b07application.ui.student;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uoft.b07application.R;

public class StudentComplaintActivity extends StudentActivity {
    Button submit_button;
    FirebaseDatabase database;
    private EditText editTextComplaint;
    private boolean isTopicSelected = false;


    String topic;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_student_complaint;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.complaint_drawer_layout);
        navigationView = findViewById(R.id.nav_complaint_view);
        toolbar = findViewById(R.id.complaint_toolbar);
    }
    @Override
    public void setButtonListeners(){
        Spinner spinner = findViewById(R.id.spinner);


        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.dropdown_items, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // Apply the adapter to the spinner

        spinner.setAdapter(adapter);
        editTextComplaint = findViewById(R.id.editTextComplaint);
        submit_button = findViewById(R.id.submit_complaint);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference("complaints");
        // Set the spinner's on item selected listener
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                // Get the selected item
                topic = parent.getItemAtPosition(position).toString();
                if(topic!="Complaint Topic")
                {isTopicSelected = true;}
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                isTopicSelected = false;
            }

        });
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!isTopicSelected) {
                    Toast.makeText(StudentComplaintActivity.this, "Please select a topic", Toast.LENGTH_SHORT).show();
                    return;}

//                EditText complaint_topic = findViewById(R.id.complaint_topic);
//                String topic = complaint_topic.getText().toString().trim();

                String complaint_body = editTextComplaint.getText().toString().trim();
                Complaint complaint = new Complaint(topic, complaint_body);
                if (!complaint_body.isEmpty() && isTopicSelected) {
                    // Generate a unique key for the complaint
                    String complaintId = reference.child("complaints").push().getKey();

                    reference.child(complaintId).setValue(complaint)
                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    // Handle success
                                    Toast.makeText(StudentComplaintActivity.this, "Complaint submitted", Toast.LENGTH_SHORT).show();
                                }
                            })
                            .addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    // Handle failure
                                    Toast.makeText(StudentComplaintActivity.this, "Failed to submit complaint", Toast.LENGTH_SHORT).show();
                                }
                            });
                } else {
                    editTextComplaint.setError("Please enter a complaint");
                }

            }
        });
    }
}