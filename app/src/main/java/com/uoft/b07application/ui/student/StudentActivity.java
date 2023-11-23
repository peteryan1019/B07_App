package com.uoft.b07application.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

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
import com.uoft.b07application.ui.menu.MenuActivity;

public class StudentActivity extends MenuActivity {
    ImageButton studentPOSTCheckerButton, studentComplaintButton,
            studentInboxButton, studentProfileButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_student;
    }
    @Override
    public void setButtonListeners(){
        studentPOSTCheckerButton = findViewById(R.id.student_POST_checker_button);
        studentComplaintButton = findViewById(R.id.student_complaint_button);
        studentInboxButton = findViewById(R.id.student_inbox_button);
        studentProfileButton = findViewById(R.id.student_profile_button);

        //buttons event on dashboards
        studentPOSTCheckerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, StudentPOSTCheckerActivity.class);
                startActivity(intent);
            }
        });
        studentComplaintButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, StudentComplaintActivity.class);
                startActivity(intent);
            }
        });
        studentInboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, StudentInboxActivity.class);
                startActivity(intent);
            }
        });
        studentProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(StudentActivity.this, ProfileActivity.class);
                startActivity(intent);
            }
        });
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.student_drawer_layout);
        navigationView = findViewById(R.id.nav_student_view);
        toolbar = findViewById(R.id.student_toolbar);
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
        } else if (menuItem.getItemId() == R.id.nav_student_profile) {
            intent = new Intent(StudentActivity.this, ProfileActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_student_logout) {
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