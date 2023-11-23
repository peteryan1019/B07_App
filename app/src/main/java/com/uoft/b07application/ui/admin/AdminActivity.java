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

public class AdminActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ComponentActivity componentActivity;
    //to handle onBackPressedDispatcher method for menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;
    ImageButton adminAnnouncementButton, adminEventButton,
            adminReviewcommentsButton, adminProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        adminAnnouncementButton = findViewById(R.id.admin_announcements_button);
        adminEventButton = findViewById(R.id.admin_event_button);
        adminReviewcommentsButton = findViewById(R.id.admin_reviewcomments_button);
        adminProfileButton = findViewById(R.id.admin_profile_button);

        //buttons event on dashboards
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
        adminReviewcommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminReviewCommentsActivity.class);
                startActivity(intent);
            }
        });
        adminProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });

        //side menu

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView = findViewById(R.id.nav_admin_view);
        toolbar = findViewById(R.id.admin_toolbar);

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.admin_navigation_drawer_open, R.string.admin_navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);


    }

    public void menuOnPress() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            OnBackPressedDispatcher onBackPressedDispatcher = componentActivity.getOnBackPressedDispatcher();
            onBackPressedDispatcher.onBackPressed();
        }

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
            intent = new Intent(AdminActivity.this, ProfileActivity.class);
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