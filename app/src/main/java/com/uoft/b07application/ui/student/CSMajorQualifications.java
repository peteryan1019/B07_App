package com.uoft.b07application.ui.student;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.uoft.b07application.R;

public class CSMajorQualifications extends AppCompatActivity {
    int yes_count = 0;
    int disabledBackgroundColor = android.graphics.Color.parseColor("#b6d7a8");
    boolean inStream = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_csmajor_qualifications);
        //button ids
        Button os_only_q4_y = findViewById(R.id.os_only_q4_y);
        Button os_only_q4_n = findViewById(R.id.os_only_q4_n);
        Button q_1_y = findViewById(R.id.q1_y);
        Button q_1_n = findViewById(R.id.q1_n);
        Button q_2_y = findViewById(R.id.q2_y);
        Button q_2_n = findViewById(R.id.q2_n);
        Button q_3_y = findViewById(R.id.q3_y);
        Button q_3_n = findViewById(R.id.q3_n);

        q_1_y.setEnabled(false);
        q_1_n.setEnabled(false);
        q_2_y.setEnabled(false);
        q_2_n.setEnabled(false);
        q_3_y.setEnabled(false);
        q_3_n.setEnabled(false);
        os_only_q4_y.setEnabled(false);
        os_only_q4_n.setEnabled(false);

    }


    public void inStream(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button outstream = findViewById(R.id.os_button);
        outstream.setEnabled(false);
        Button os_q4_only_y = findViewById(R.id.os_only_q4_y);
        Button os_q4_only_n = findViewById(R.id.os_only_q4_n);
        os_q4_only_n.setEnabled(false);
        os_q4_only_y.setEnabled(false);

        //make relevant buttons available
        Button q_1_y = findViewById(R.id.q1_y);
        Button q_1_n = findViewById(R.id.q1_n);
        Button q_2_y = findViewById(R.id.q2_y);
        Button q_2_n = findViewById(R.id.q2_n);
        Button q_3_y = findViewById(R.id.q3_y);
        Button q_3_n = findViewById(R.id.q3_n);

        q_1_y.setEnabled(true);
        q_1_n.setEnabled(true);
        q_2_y.setEnabled(true);
        q_2_n.setEnabled(true);
        q_3_y.setEnabled(true);
        q_3_n.setEnabled(true);


    }

    public void outStream(View view){
        inStream = false;
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button instream = findViewById(R.id.is_button);
        instream.setEnabled(false);
        //make relevant buttons available
        Button q_1_y = findViewById(R.id.q1_y);
        Button q_1_n = findViewById(R.id.q1_n);
        Button q_2_y = findViewById(R.id.q2_y);
        Button q_2_n = findViewById(R.id.q2_n);
        Button q_3_y = findViewById(R.id.q3_y);
        Button q_3_n = findViewById(R.id.q3_n);
        Button os_only_q4_y = findViewById(R.id.os_only_q4_y);
        Button os_only_q4_n = findViewById(R.id.os_only_q4_n);

        q_1_y.setEnabled(true);
        q_1_n.setEnabled(true);
        q_2_y.setEnabled(true);
        q_2_n.setEnabled(true);
        q_3_y.setEnabled(true);
        q_3_n.setEnabled(true);
        os_only_q4_y.setEnabled(true);
        os_only_q4_n.setEnabled(true);
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
        if(inStream && yes_count == 3){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }
        else if(yes_count == 4){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }
    }

    public void q2_yes(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q2_n);
        corr_no.setEnabled(false);
        yes_count++;
        Log.d("Current yes count", "The current number of yes is: " + yes_count);
        if(inStream && yes_count == 3){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }
        else if(yes_count == 4){
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
        if(inStream && yes_count == 3){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }
        else if(yes_count == 4){
            TextView textView = (TextView) findViewById(R.id.qualification_status);
            textView.setText("QUALIFIED");
        }

    }

    public void q4_yes(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.os_only_q4_n);
        corr_no.setEnabled(false);
        yes_count++;
        //if not an out of stream and all answers were yes before this make them qualified
        if(yes_count == 4){
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

    public void q2_no(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q2_y);
        corr_no.setEnabled(false);
    }

    public void q3_no(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.q3_y);
        corr_no.setEnabled(false);
    }

    public void q4_no(View view){
        view.setEnabled(false);
        view.setBackgroundColor(disabledBackgroundColor);
        Button corr_no = findViewById(R.id.os_only_q4_y);
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