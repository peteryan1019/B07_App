package com.uoft.b07application.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.uoft.b07application.R;

public class AdminAnnouncementActivity extends AdminActivity {
    private AnnouncementDialog.DialogDismissListener dismissListener;
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
        announcementDialog.show(getSupportFragmentManager(), "announcement dialog");
        dismissListener = new AnnouncementDialog.DialogDismissListener() {
            @Override
            public void onDialogDismissed() {
                drawerLayout.requestFocus();
                navigationView.requestFocus();
                toolbar.requestFocus();
            }
        };
        announcementDialog.setDismissListener(dismissListener);
    }
}