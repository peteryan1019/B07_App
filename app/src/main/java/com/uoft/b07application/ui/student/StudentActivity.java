package com.uoft.b07application.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.activity.ComponentActivity;
import androidx.activity.OnBackPressedDispatcher;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.admin.AdminActivity;
import com.uoft.b07application.ui.admin.AdminAnnouncementActivity;
import com.uoft.b07application.ui.admin.AdminEventActivity;
import com.uoft.b07application.ui.ProfileActivity;
import com.uoft.b07application.ui.admin.AdminReviewCommentsActivity;
import com.uoft.b07application.ui.login.LoginActivity;

public class StudentActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private ComponentActivity componentActivity;
    //to handle onBackPressedDispatcher method for menu
    DrawerLayout drawerLayout;
    NavigationView navigationView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        drawerLayout = findViewById(R.id.student_drawer_layout);
        navigationView = findViewById(R.id.nav_student_view);
        toolbar = findViewById(R.id.student_toolbar);

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
        if (menuItem.getItemId() == R.id.nav_POST_checker) {
            intent = new Intent(StudentActivity.this, StudentPOSTCheckerActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_complaints) {
            intent = new Intent(StudentActivity.this, StudentComplaintActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_inbox) {
            intent = new Intent(StudentActivity.this, StudentInboxActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_profile) {
            intent = new Intent(StudentActivity.this, ProfileActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_admin_logout) {
            intent = new Intent(StudentActivity.this, LoginActivity.class);
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