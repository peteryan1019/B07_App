package com.uoft.b07application.ui.student;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uoft.b07application.R;

public class StudentInboxActivity extends StudentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_student_inbox;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.student_inbox_drawer_layout);
        navigationView = findViewById(R.id.nav_student_inbox_view);
        toolbar = findViewById(R.id.student_inbox_toolbar);
    }
    @Override
    public void setButtonListeners(){

    }
}