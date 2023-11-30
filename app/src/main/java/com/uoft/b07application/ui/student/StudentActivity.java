package com.uoft.b07application.ui.student;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.menu.MenuActivity;


public class StudentActivity extends MenuActivity {
        public String name;
        public String email;
        public String username;
        public String isadminorstudent;
        ImageButton studentPOSTCheckerButton, studentComplaintButton,
                studentInboxButton, studentProfileButton, studentEventButton;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
        }

        @Override
        protected int setLayoutId() {
            return R.layout.activity_student;
        }

        @Override
        public void setButtonListeners() {
            //move it here
            handleIntentExtra();
            Intent i = getIntent();

            studentPOSTCheckerButton = findViewById(R.id.student_POST_checker_button);
            studentComplaintButton = findViewById(R.id.student_complaint_button);
            studentInboxButton = findViewById(R.id.student_inbox_button);
            studentProfileButton = findViewById(R.id.student_profile_button);
            studentEventButton = findViewById(R.id.student_event_button);
            //buttons event on dashboards
            studentPOSTCheckerButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentActivity.this, StudentPOSTCheckerActivity.class);
                    putExtras(i, intent);
                    startActivity(intent);
                }
            });
            studentComplaintButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentActivity.this, StudentComplaintActivity.class);
                    putExtras(i, intent);
                    startActivity(intent);
                }
            });
            studentInboxButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentActivity.this, StudentInboxActivity.class);
                    putExtras(i, intent);
                    startActivity(intent);
                }
            });
            studentProfileButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentActivity.this, StudentProfileActivity.class);
                    putExtras(i, intent);
                    startActivity(intent);
                }
            });
            studentEventButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(StudentActivity.this, StudentEventActivity.class);
                    putExtras(i, intent);
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
            } else if (menuItem.getItemId() == R.id.nav_event) {
                intent = new Intent(StudentActivity.this, StudentEventActivity.class);
            } else if (menuItem.getItemId() == R.id.nav_student_logout) {
                intent = new Intent(StudentActivity.this, LoginActivity.class);
            } else if (menuItem.getItemId() == R.id.nav_student_profile) {
                intent = new Intent(StudentActivity.this, StudentProfileActivity.class);
            }
            //switch statement does not work in this case for some reason
            if (intent != null) {
                putExtras(i, intent);
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
        void putExtras(Intent i, Intent intent){
            String username = i.getStringExtra("username");
            String email = i.getStringExtra("email");
            String isadminorstudent = i.getStringExtra("isadminorstudent");
            String name = i.getStringExtra("name");
            intent.putExtra("name", name);
            intent.putExtra("email", email);
            intent.putExtra("isadminorstudent", isadminorstudent);
            intent.putExtra("username", username);
        }

    }