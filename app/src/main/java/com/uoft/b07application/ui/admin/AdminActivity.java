package com.uoft.b07application.ui.admin;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import com.google.android.material.navigation.NavigationView;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.ProfileActivity;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.login.SignupActivity;
import com.uoft.b07application.ui.menu.MenuActivity;


public class AdminActivity extends MenuActivity {
    private ImageButton adminAnnouncementButton, adminEventButton,
            adminReviewCommentsButton, adminProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin;
    }
    @Override
    protected void setAdminMenu(){
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_view);
        toolbar = findViewById(R.id.admin_toolbar);
    }
    @Override
    protected void setButtonListeners(){
        adminAnnouncementButton = findViewById(R.id.admin_announcements_button);
        adminEventButton = findViewById(R.id.admin_event_button);
        adminReviewCommentsButton = findViewById(R.id.admin_reviewcomments_button);
        adminProfileButton = findViewById(R.id.admin_profile_button);
        adminAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminAnnouncementActivity.class);
                startActivity(intent);
            }
        });
        adminEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminEventActivity.class);
                startActivity(intent);
            }
        });
        adminReviewCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminReviewCommentsActivity.class);
                startActivity(intent);
            }
        });
        adminProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent = null;
        if (menuItem.getItemId() == R.id.nav_announcement) {
            intent = new Intent(AdminActivity.this, AdminAnnouncementActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_departmentEvent) {
            intent = new Intent(AdminActivity.this, AdminEventActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_reviewComments) {
            intent = new Intent(AdminActivity.this, AdminReviewCommentsActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_admin_profile) {
            intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_admin_logout) {
            intent = new Intent(AdminActivity.this, LoginActivity.class);
        }
        //switch statement does not work in this case for some reason
        if (intent != null) {
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        return false;
    }
}