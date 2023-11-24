package com.uoft.b07application.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.ComponentActivity;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.navigation.NavigationView;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.menu.MenuActivity;


public class StudentActivity extends MenuActivity {
        public String name;
        public String email;
        public String username;
        public String isadminorstudent;
        ImageButton studentPOSTCheckerButton, studentComplaintButton,
                studentInboxButton, studentProfileButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            handleIntentExtra();
        }

        @Override
        protected int setLayoutId() {
            return R.layout.activity_student;
        }

        @Override
        public void setButtonListeners() {
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
                    Intent intent = new Intent(StudentActivity.this, StudentProfileActivity.class);
                    intent.putExtra("name", name);
                    intent.putExtra("email", email);
                    intent.putExtra("isadminorstudent", isadminorstudent);
                    intent.putExtra("username", username);
                    startActivity(intent);
                }
            });
        }

        @Override
        protected void setMenu() {
            drawerLayout = findViewById(R.id.student_drawer_layout);
            navigationView = findViewById(R.id.nav_student_view);
            toolbar = findViewById(R.id.student_toolbar);
        }

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Intent i = getIntent();
            Intent intent = null;
            if (menuItem.getItemId() == R.id.nav_POST_checker) {
                intent = new Intent(StudentActivity.this, StudentPOSTCheckerActivity.class);
            } else if (menuItem.getItemId() == R.id.nav_complaints) {
                intent = new Intent(StudentActivity.this, StudentComplaintActivity.class);
            } else if (menuItem.getItemId() == R.id.nav_inbox) {
                intent = new Intent(StudentActivity.this, StudentInboxActivity.class);
            } else if (menuItem.getItemId() == R.id.nav_student_logout) {
                intent = new Intent(StudentActivity.this, LoginActivity.class);
            } else if (menuItem.getItemId() == R.id.nav_student_profile) {
                String username = i.getStringExtra("username");
                String email = i.getStringExtra("email");
                intent = new Intent(StudentActivity.this, StudentProfileActivity.class);
                intent.putExtra("username", username);
                intent.putExtra("email", email);
            }
            //switch statement does not work in this case for some reason
            if (intent != null) {
                startActivity(intent);
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }

            return false;
        }
        // I move ur code here (Ben)
        protected void handleIntentExtra(){
            Intent i = getIntent();
            username = i.getStringExtra("username");
            name = i.getStringExtra("name");
            email = i.getStringExtra("email");
            isadminorstudent = i.getStringExtra("isadminorstudent");
            Log.d("StudentActivity", "name is : " + name);
            Log.d("StudentActivity", "email is : " + email);
        }

    }