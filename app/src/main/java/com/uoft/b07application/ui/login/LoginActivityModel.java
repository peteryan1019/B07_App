package com.uoft.b07application.ui.login;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivityModel {

    private String username;
    private String hashedInputPassword;

    public interface OnLoginFinishedListener {
        void onSuccess(String username, String name, String email, boolean isAdminOrStudent);
        void onError(String errorMessage);
    }

    public void loginUser(String username, String password, OnLoginFinishedListener listener) {
        this.username = username;
        this.hashedInputPassword = PasswordHasher.hashPassword(password);

        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        Query checkAdmin = usersRef.child("admins").orderByChild("username").equalTo(username);
        checkAdminCredentials(checkAdmin, listener);
    }

    private void checkAdminCredentials(Query adminQuery, OnLoginFinishedListener listener) {
        adminQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    handleValidAdmin(snapshot, listener);
                } else {
                    checkStudentCredentials(listener);
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    private void handleValidAdmin(DataSnapshot snapshot, OnLoginFinishedListener listener) {
        for (DataSnapshot adminSnapshot : snapshot.getChildren()) {
            String passwordFromDB = adminSnapshot.child("password").getValue(String.class);
            if (passwordFromDB != null && passwordFromDB.equals(hashedInputPassword)) {
                String nameFromDB = adminSnapshot.child("name").getValue(String.class);
                String emailFromDB = adminSnapshot.child("email").getValue(String.class);
                listener.onSuccess(username, nameFromDB, emailFromDB, true);
                return;
            }
        }
        listener.onError("Invalid Admin Credentials");
    }

    private void checkStudentCredentials(OnLoginFinishedListener listener) {
        DatabaseReference studentsRef = FirebaseDatabase.getInstance().getReference("users").child("students");
        Query studentQuery = studentsRef.orderByChild("username").equalTo(username);

        studentQuery.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    handleValidStudent(snapshot, listener);
                } else {
                    listener.onError("User does not exist");
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle onCancelled
            }
        });
    }

    private void handleValidStudent(DataSnapshot snapshot, OnLoginFinishedListener listener) {
        for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
            String passwordFromDB = studentSnapshot.child("password").getValue(String.class);
            if (passwordFromDB != null && passwordFromDB.equals(hashedInputPassword)) {
                String nameFromDB = studentSnapshot.child("name").getValue(String.class);
                String emailFromDB = studentSnapshot.child("email").getValue(String.class);
                listener.onSuccess(username, nameFromDB, emailFromDB, false);
                return;
            }
        }
        listener.onError("Invalid Student Credentials");
    }
}
