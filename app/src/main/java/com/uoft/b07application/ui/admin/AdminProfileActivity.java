package com.uoft.b07application.ui.admin;

import android.os.Bundle;

import com.uoft.b07application.R;

public class AdminProfileActivity extends AdminActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin_profile;
    }
    @Override
    protected void setAdminMenu(){
        drawerLayout = findViewById(R.id.admin_profile_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_profile_view);
        toolbar = findViewById(R.id.admin_profile_toolbar);
    }
    @Override
    protected void setButtonListeners(){

    }
}