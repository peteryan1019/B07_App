package com.uoft.b07application.ui.student;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.content.Intent;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uoft.b07application.R;

import java.util.HashMap;

public class StudentFeedback extends AppCompatActivity {
    private EditText nameEditText;
    private EditText eventEditText;
    private RatingBar ratingBar;
    private EditText commentEditText;
    private Button submitButton;
    private DatabaseReference feedbackReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_feedback_layout);

        // Initialize UI elements

        commentEditText = findViewById(R.id.commentEditText);
        submitButton = findViewById(R.id.submitButton);

        // Initialize Firebase
        feedbackReference = FirebaseDatabase.getInstance().getReference().child("feedback");

        // Set click listener for when the user submits the form
        submitButton.setOnClickListener(view -> submitFeedback());
    }

    private void saveFeedbackToFirebase(float rating, String feedbackComment) {
        //getExtra
        Intent intent = getIntent();
        // Get the reference to the "feedback" node in the database
        DatabaseReference feedbackReference = FirebaseDatabase.getInstance().getReference().child("feedback");

        HashMap<String, String> feedback = new HashMap<>();
        feedback.put("commenterName", intent.getStringExtra("commenterName"));
        feedback.put("commenterEmail", intent.getStringExtra("commenterEmail"));
        feedback.put("eventKey", intent.getStringExtra("eventKey"));
        feedback.put("eventName", intent.getStringExtra("eventName"));
        feedback.put("rating", Float.toString(rating));
        feedback.put("feedbackComment", feedbackComment);


        // Save the feedback to Firebase
        feedbackReference.push().setValue(feedback)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // Handle success
                        Toast.makeText(StudentFeedback.this, "Feedback saved to Firebase", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Handle failure
                        Toast.makeText(StudentFeedback.this, "Failed to save feedback to Firebase", Toast.LENGTH_SHORT).show();
                    }
                });
    }


    private void submitFeedback() {

        String ratingText = ((EditText) findViewById(R.id.ratingEditText)).getText().toString().trim();
        String feedbackComment = ((EditText) findViewById(R.id.commentEditText)).getText().toString().trim();

        float rating = 0;
        if (!ratingText.isEmpty()) {
            rating = Float.parseFloat(ratingText);
        }

        if (rating >= 1 && rating <= 10 && !feedbackComment.isEmpty()) {

            findViewById(R.id.ratingLabel).setVisibility(View.GONE);
            findViewById(R.id.ratingEditText).setVisibility(View.GONE);
            findViewById(R.id.commentsLabel).setVisibility(View.GONE);
            findViewById(R.id.commentEditText).setVisibility(View.GONE);
            findViewById(R.id.submitButton).setVisibility(View.GONE);

            Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();

            // Save feedback to Firebase
            saveFeedbackToFirebase(rating, feedbackComment);

            finish();
        } else {
            // Display an error message if any of the fields are empty or invalid
            Toast.makeText(this, "Please fill in all fields correctly", Toast.LENGTH_SHORT).show();
        }
    }

}
