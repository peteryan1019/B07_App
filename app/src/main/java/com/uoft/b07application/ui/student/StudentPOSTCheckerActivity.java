package com.uoft.b07application.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.uoft.b07application.R;

public class StudentPOSTCheckerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_postchecker);
    }

    public void checkCSmajor(View view){
        Intent i = new Intent(this, CSMajorQualifications.class);
        startActivity(i);
    }

    public void checkCSspec(View view){
        Intent i = new Intent(this, CSSpecQualifications.class);
        startActivity(i);
    }
}