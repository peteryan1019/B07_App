package com.uoft.b07application.ui.login;
import com.uoft.b07application.ui.student.StudentActivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.admin.AdminActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText loginUsername, loginPassword;
    private Button loginButton;
    private TextView signupRedirectText;
    private LoginActivityPresenter loginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        loginPresenter = new LoginActivityPresenter(this, new LoginActivityModel());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = loginUsername.getText().toString().trim();
                String password = loginPassword.getText().toString().trim();
                loginPresenter.validateCredentials(username, password);
            }
        });

        signupRedirectText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, SignupActivity.class);
                startActivity(intent);
            }
        });

    }

    public void setUsernameError(String errorMessage) {
        loginUsername.setError(errorMessage);
    }

    public void setPasswordError(String errorMessage) {
        loginPassword.setError(errorMessage);
    }

    public void showLoginError(String errorMessage) {
        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show();
    }

    public void navigateToAdminScreen(String username, String name, String email) {
        Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("isadmin", "Is Admin");
        startActivity(intent);
    }

    public void navigateToStudentScreen(String username, String name, String email) {
        Intent intent = new Intent(LoginActivity.this, StudentActivity.class);
        intent.putExtra("username", username);
        intent.putExtra("name", name);
        intent.putExtra("email", email);
        intent.putExtra("isadminorstudent", "Is Student");
        startActivity(intent);
    }
}
