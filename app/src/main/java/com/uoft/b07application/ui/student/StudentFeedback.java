package com.uoft.b07application.ui.student;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
//need to import the layout stuff
import com.uoft.b07application.R;
import com.uoft.b07application.ui.admin.AdminEventActivity;

import android.content.Intent;


import androidx.appcompat.app.AppCompatActivity;
public class StudentFeedback extends AppCompatActivity{
    private EditText commentEditText;
    private TextView feedbackPromptTextView;
    private TextView thankYouMessageTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_feedback_layout);

        // Initialize UI elements
        commentEditText = findViewById(R.id.commentEditText);
        Button submitButton = findViewById(R.id.submitButton);
        feedbackPromptTextView = findViewById(R.id.feedbackPromptTextView);
        thankYouMessageTextView = null;

        // Set click listener for when user submits form
        submitButton.setOnClickListener(view -> submitFeedback());
    }

    private void submitFeedback() {
        // Get the user to write feedback
        String feedbackComment = commentEditText.getText().toString().trim();

        if (!feedbackComment.isEmpty()) {

            // Hide the feedback prompt and comment field
            feedbackPromptTextView.setVisibility(View.GONE);
            commentEditText.setVisibility(View.GONE);

            // Display a toast message
            Toast.makeText(this, "Feedback submitted successfully", Toast.LENGTH_SHORT).show();

            // After submitting feedback, navigate back to EventPageActivity
            Intent intent = new Intent(this, StudentEventActivity.class);
            startActivity(intent);

            // Close the current activity (StudentFeedback)
            finish();

        } else {
            // Display an error message if the comment is empty
            Toast.makeText(this, "Please enter your feedback", Toast.LENGTH_SHORT).show();
        }
    }

}
