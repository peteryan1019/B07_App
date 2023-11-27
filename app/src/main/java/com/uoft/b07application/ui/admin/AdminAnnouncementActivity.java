package com.uoft.b07application.ui.admin;

import android.os.Bundle;

import com.uoft.b07application.R;

public class AdminAnnouncementActivity extends AdminActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin_announcement;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.announcement_drawer_layout);
        navigationView = findViewById(R.id.nav_announcement_view);
        toolbar = findViewById(R.id.announcement_toolbar);
    }
    @Override
    public void setButtonListeners(){

    }

}