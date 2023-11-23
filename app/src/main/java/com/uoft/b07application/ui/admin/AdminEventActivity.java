package com.uoft.b07application.ui.admin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.uoft.b07application.R;

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
    protected void setAdminMenu(){
        drawerLayout = findViewById(R.id.admin_event_drawer_layout);
        navigationView = findViewById(R.id.nav_event_view);
        toolbar = findViewById(R.id.admin_event_toolbar);
    }
    @Override
    protected void setButtonListeners(){

    }
}