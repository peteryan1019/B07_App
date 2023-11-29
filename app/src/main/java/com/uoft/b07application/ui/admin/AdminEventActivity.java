package com.uoft.b07application.ui.admin;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.student.StudentFeedback;

public class AdminEventActivity extends AdminActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin_event;

    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.admin_event_drawer_layout);
        navigationView = findViewById(R.id.nav_event_view);
        toolbar = findViewById(R.id.admin_event_toolbar);
    }
    @Override
    protected void setButtonListeners(){
    }
}