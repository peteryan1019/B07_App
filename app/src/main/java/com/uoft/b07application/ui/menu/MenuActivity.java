package com.uoft.b07application.ui.menu;

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

public abstract class MenuActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    protected DrawerLayout drawerLayout;
    protected NavigationView navigationView;
    protected Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutId());

        //set listeners on this page
        setButtonListeners();

        // set side menu
        setAdminMenu();

        setSupportActionBar(toolbar);

        navigationView.bringToFront();
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.admin_navigation_drawer_open, R.string.admin_navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }

    protected abstract int setLayoutId();

    protected void setAdminMenu(){
        drawerLayout = findViewById(R.id.admin_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_view);
        toolbar = findViewById(R.id.admin_toolbar);
    }
    protected abstract void setButtonListeners();
    public abstract boolean onNavigationItemSelected(@NonNull MenuItem menuItem);
}