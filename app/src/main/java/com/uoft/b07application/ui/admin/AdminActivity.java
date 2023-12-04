package com.uoft.b07application.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.core.view.GravityCompat;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.student.StudentProfileActivity;
import com.uoft.b07application.ui.login.LoginActivity;
import com.uoft.b07application.ui.menu.MenuActivity;


public class AdminActivity extends MenuActivity {
    String name;
    String email;
    String isadminorstudent;
    String username;
    ImageButton adminAnnouncementButton,  adminEventButton,  adminReviewCommentsButton,  adminProfileButton, adminDashboardButton;
Button adminEventButton_1, adminAnnouncementButton_1, adminReviewCommentsButton_1, adminProfileButton_1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent i = getIntent();
        username = i.getStringExtra("username");
        name = i.getStringExtra("name");
        email = i.getStringExtra("email");
        isadminorstudent = i.getStringExtra("isadminorstudent");
        Log.d("username", "username is" +username);
        Log.d("name", "name is" +name);
        Log.d("email", "email is" +email);
        Log.d("admin state", "admin state is" + isadminorstudent);
        super.onCreate(savedInstanceState);
    }

//    @Override
//    protected void setUpAdminDashboardButton() {
//        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
//            adminDashboardButton = findViewById(R.id.nav_app_icon);
//            adminDashboardButton.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent i = getIntent();
//                    Intent intent = new Intent(AdminActivity.this, AdminActivity.class);
//                    putExtras(i, intent);
//                    startActivity(intent);
//                }
//            });
//        }
//    }

    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_view);
        toolbar = findViewById(R.id.admin_toolbar);
    }
    @Override
    protected void setButtonListeners(){
        handleIntentExtra();
        Intent i = getIntent();
        adminAnnouncementButton = findViewById(R.id.admin_announcements_button);
        adminAnnouncementButton_1 = findViewById(R.id.admin_announcements_text);
        adminEventButton = findViewById(R.id.admin_event_button);
        adminEventButton_1=findViewById(R.id.admin_event_text);



        adminReviewCommentsButton = findViewById(R.id.admin_reviewcomments_button);
        adminReviewCommentsButton_1=findViewById(R.id.admin_reviewcomments_text);
        adminProfileButton = findViewById(R.id.admin_profile_button);
        adminProfileButton_1=findViewById(R.id.admin_profile_text);
        adminAnnouncementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminAnnouncementActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
        adminAnnouncementButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminAnnouncementActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
        adminEventButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminEventActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
        adminEventButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminEventActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
        adminReviewCommentsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminReviewCommentsActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });

        adminReviewCommentsButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminReviewCommentsActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
        adminProfileButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
        adminProfileButton_1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
                putExtras(i, intent);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent i = getIntent();
        Intent intent = null;
        if (menuItem.getItemId() == R.id.nav_announcement) {
            intent = new Intent(AdminActivity.this, AdminAnnouncementActivity.class);
            intent.putExtra("username", username);
            intent.putExtra("email", email);
            putExtras(i, intent);
        } else if (menuItem.getItemId() == R.id.nav_departmentEvent) {
            intent = new Intent(AdminActivity.this, AdminEventActivity.class);
            putExtras(i, intent);
        } else if (menuItem.getItemId() == R.id.nav_reviewComments) {
            intent = new Intent(AdminActivity.this, AdminReviewCommentsActivity.class);
            putExtras(i, intent);
        } else if (menuItem.getItemId() == R.id.nav_admin_profile) {
            intent = new Intent(AdminActivity.this, AdminProfileActivity.class);
            intent.putExtra("username", name);
            intent.putExtra("email", email);
        } else if (menuItem.getItemId() == R.id.nav_admin_logout) {
            intent = new Intent(AdminActivity.this, LoginActivity.class);
        } else if (menuItem.getItemId() == R.id.nav_home){
            intent = new Intent(AdminActivity.this, AdminActivity.class);
            putExtras(i, intent);
        }
        //switch statement does not work in this case for some reason
        if (intent != null) {
            //putExtras(i, intent);
            startActivity(intent);
            drawerLayout.closeDrawer(GravityCompat.START);
            return true;
        }

        return false;
    }
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