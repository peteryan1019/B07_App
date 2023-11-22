package com.uoft.b07application.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.uoft.b07application.R;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_profile);
        Intent i = getIntent();
        String un = i.getStringExtra("username");
        String em = i.getStringExtra("email");
        TextView user_name = (TextView) findViewById(R.id.uns);
        user_name.setText(un);
        TextView user_email = (TextView) findViewById(R.id.ems);
        user_name.setText(em);
    }


}