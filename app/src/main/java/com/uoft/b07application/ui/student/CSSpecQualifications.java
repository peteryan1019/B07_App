package com.uoft.b07application.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uoft.b07application.R;

public class CSSpecQualifications extends AppCompatActivity {
    int no_count;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csspec_qualifications);
    }


    public void inStream(View view){
        view.setEnabled(false);
        Button outstream = findViewById(R.id.os_button);
        outstream.setEnabled(false);
        Button os_q4_only_y = findViewById(R.id.os_only_q4_y);
        Button os_q4_only_n = findViewById(R.id.os_only_q4_n);
        os_q4_only_n.setEnabled(false);
        os_q4_only_y.setEnabled(false);
    }

    public void outStream(View view){
        view.setEnabled(false);
        Button instream = findViewById(R.id.is_button);
        instream.setEnabled(false);
    }
    //indiviudal questions start
    //yes responses
    public void q1_yes(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.q1_n);
        corr_no.setEnabled(false);
    }

    public void q2_yes(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.q2_n);
        corr_no.setEnabled(false);
    }

    public void q3_yes(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.q3_n);
        corr_no.setEnabled(false);
        Button os_q4 = findViewById(R.id.os_only_q4_y);
        //if not an out of stream and all answers were yes before this make them qualified
        if(!os_q4.isEnabled() && no_count==0){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }

    }

    public void q4_yes(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.os_only_q4_n);
        corr_no.setEnabled(false);
        //if not an out of stream and all answers were yes before this make them qualified
        if(no_count==0){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }

    }
    //no responses

    public void q1_no(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.q1_y);
        corr_no.setEnabled(false);
        no_count++;
    }

    public void q2_no(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.q2_y);
        corr_no.setEnabled(false);
        no_count++;
    }

    public void q3_no(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.q3_y);
        corr_no.setEnabled(false);
        no_count++;

    }

    public void q4_no(View view){
        view.setEnabled(false);
        Button corr_no = findViewById(R.id.os_only_q4_y);
        corr_no.setEnabled(false);
        no_count++;

    }

    public void restart(View view){
        no_count = 0;
        Button q1_y = findViewById(R.id.q1_y);
        q1_y.setEnabled(true);
        Button q2_y = findViewById(R.id.q2_y);
        q2_y.setEnabled(true);
        Button q3_y = findViewById(R.id.q3_y);
        q3_y.setEnabled(true);
        Button q4_y = findViewById(R.id.os_only_q4_y);
        q4_y.setEnabled(true);
        Button q1_n = findViewById(R.id.q1_n);
        q1_n.setEnabled(true);
        Button q2_n = findViewById(R.id.q2_n);
        q2_n.setEnabled(true);
        Button q3_n = findViewById(R.id.q3_n);
        q3_n.setEnabled(true);
        Button q4_n = findViewById(R.id.os_only_q4_n);
        q4_n.setEnabled(true);

        Button outstream = findViewById(R.id.os_button);
        outstream.setEnabled(true);
        Button instream = findViewById(R.id.is_button);
        instream.setEnabled(true);

        TextView textView = (TextView) findViewById(R.id.qualification_status);
        textView.setText("Not qualified");


    }
}