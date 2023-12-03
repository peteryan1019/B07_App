package com.uoft.b07application.ui.login;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.switchmaterial.SwitchMaterial;

import com.uoft.b07application.R;

public class SignupActivity extends AppCompatActivity {

    private EditText signupName, signupUsername, signupEmail, signupPassword;
    private TextView loginRedirectText;
    private Button signupButton;
    private SignupActivityPresenter signupPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        signupName = findViewById(R.id.signup_name);
        signupEmail = findViewById(R.id.signup_email);
        signupUsername = findViewById(R.id.signup_username);
        signupPassword = findViewById(R.id.signup_password);
        loginRedirectText = findViewById(R.id.loginRedirectText);
        SwitchMaterial adminSwitch = findViewById(R.id.admin_switch);
        signupButton = findViewById(R.id.signup_button);

        signupPresenter = new SignupActivityPresenter(this, new SignupActivityModel());

        signupButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String name = signupName.getText().toString();
                String email = signupEmail.getText().toString().toLowerCase();
                final String username = signupUsername.getText().toString();
                final String password = signupPassword.getText().toString();
                final boolean isAdmin = adminSwitch.isChecked();

                signupPresenter.validateSignup(name, email, username, password, isAdmin);
            }
        });

        loginRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    public void showSignupError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void showSignupSuccess() {
        Toast.makeText(this, "You have signed up successfully!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
        startActivity(intent);
    }
}
