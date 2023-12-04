package com.uoft.b07application.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uoft.b07application.R;

public class CSMinorQualifications extends AppCompatActivity {

    int yes_count = 0;
    int disabledBackgroundColor = android.graphics.Color.parseColor("#b6d7a8");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csminor_qualifications);
    }

    //indiviudal questions start
    //yes responses
    public void q1_yes(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q1_n);
        corr_no.setEnabled(false);
        yes_count++;
        Log.d("Current yes count", "The current number of yes is: " + yes_count);
        if(yes_count == 2){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }
    }

    public void q3_yes(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q3_n);
        corr_no.setEnabled(false);
        Button os_q4 = findViewById(R.id.os_only_q4_y);
        //if not an out of stream and all answers were yes before this make them qualified
        yes_count++;
        Log.d("Current yes count", "The current number of yes is: " + yes_count);
        if(yes_count == 2){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }
    }

    //no responses

    public void q1_no(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q1_y);
        corr_no.setEnabled(false);
    }

    public void q3_no(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q3_y);
        corr_no.setEnabled(false);
    }

    public void restart(View view){

        Intent restart = new Intent(this, CSMajorQualifications.class);
        startActivity(restart);
    }

    public void goBackToPostPages(View view){
        Intent PostPages = new Intent(this, StudentPOSTCheckerActivity.class);
        startActivity(PostPages);
    }
}