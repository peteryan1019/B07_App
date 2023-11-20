package com.uoft.b07application.ui.login;
import com.uoft.b07application.ui.MainActivity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;
import com.uoft.b07application.ui.admin.AdminActivity;

import java.util.Objects;

public class LoginActivity extends AppCompatActivity {

    EditText loginUsername, loginPassword;
    Button loginButton;
    TextView signupRedirectText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginUsername = findViewById(R.id.login_username);
        loginPassword = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);
        signupRedirectText = findViewById(R.id.signupRedirectText);

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!validateUsername() | !validatePassword()) {

                } else {
                    checkUser();
                }
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

    public Boolean validateUsername() {
        String val = loginUsername.getText().toString();
        if (val.isEmpty()) {
            loginUsername.setError("Username cannot be empty");
            return false;
        } else {
            loginUsername.setError(null);
            return true;
        }
    }

    public Boolean validatePassword(){
        String val = loginPassword.getText().toString();
        if (val.isEmpty()) {
            loginPassword.setError("Password cannot be empty");
            return false;
        } else {
            loginPassword.setError(null);
            return true;
        }
    }

    public void checkUser() {
        String userUsername = loginUsername.getText().toString().trim();
        String userPassword = loginPassword.getText().toString().trim();

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        Query checkAdmin = usersRef.child("admins").orderByChild("username").equalTo(userUsername);
        Query checkStudent = usersRef.child("students").orderByChild("username").equalTo(userUsername);

        checkAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot adminSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = adminSnapshot.child("password").getValue(String.class);
                        if (passwordFromDB.equals(userPassword)) {
                            String nameFromDB = adminSnapshot.child("name").getValue(String.class);
                            String emailFromDB = adminSnapshot.child("email").getValue(String.class);

                            // User found and credentials match
                            // Proceed with your logic, like starting an activity
                            // Pass retrieved information to the next activity
                            Intent intent = new Intent(LoginActivity.this, AdminActivity.class);
                            intent.putExtra("name", nameFromDB);
                            intent.putExtra("email", emailFromDB);
                            // Add more data if needed
                            startActivity(intent);
                        } else {
                            loginPassword.setError("Invalid Credentials");
                            loginPassword.requestFocus();
                        }
                        return;
                    }
                } else {
                    // Admin not found, check if the user is a student
                    checkStudent.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                                    String passwordFromDB = studentSnapshot.child("password").getValue(String.class);
                                    if (passwordFromDB.equals(userPassword)) {
                                        String nameFromDB = studentSnapshot.child("name").getValue(String.class);
                                        String emailFromDB = studentSnapshot.child("email").getValue(String.class);

                                        // Student found and credentials match
                                        // Proceed with your logic, like starting an activity
                                        // Pass retrieved information to the next activity
                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        intent.putExtra("name", nameFromDB);
                                        intent.putExtra("email", emailFromDB);
                                        // Add more data if needed
                                        startActivity(intent);
                                        return;
                                    } else {
                                        loginPassword.setError("Invalid Credentials");
                                        loginPassword.requestFocus();
                                    }
                                }
                            } else {
                                loginUsername.setError("User does not exist");
                                loginUsername.requestFocus();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {
                            // Handle onCancelled
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                // Handle onCancelled
            }
        });
    }
}
