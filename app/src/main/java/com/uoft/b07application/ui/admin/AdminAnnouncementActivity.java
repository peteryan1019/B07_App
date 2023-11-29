package com.uoft.b07application.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.appcompat.app.AlertDialog;

import com.uoft.b07application.R;

public class AdminAnnouncementActivity extends AdminActivity {
    private ImageButton composeButton;

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
        composeButton = findViewById(R.id.composeButton);
        composeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openDialog();
            }
        });
    }
    private void openDialog(){
        AnnouncementDialog announcementDialog = new AnnouncementDialog();
        Intent intent = getIntent();
        announcementDialog.setSenderUsername(intent.getStringExtra("username"));
        announcementDialog.setSenderEmail(intent.getStringExtra("email"));
        announcementDialog.show(getSupportFragmentManager(), "announcement dialog");
    }

}