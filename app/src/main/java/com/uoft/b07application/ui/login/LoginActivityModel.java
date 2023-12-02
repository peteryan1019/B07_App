package com.uoft.b07application.ui.login;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class LoginActivityModel {
    public interface OnLoginFinishedListener {
        void onSuccess(String username, String name, String email, boolean isAdminOrStudent);
        void onError(String errorMessage);
    }

    public void loginUser(String username, String password, OnLoginFinishedListener listener) {
        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");
        String hashedInputPassword = PasswordHasher.hashPassword(password);

        Query checkAdmin = usersRef.child("admins").orderByChild("username").equalTo(username);
        Query checkStudent = usersRef.child("students").orderByChild("username").equalTo(username);

        checkAdmin.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    for (DataSnapshot adminSnapshot : snapshot.getChildren()) {
                        String passwordFromDB = adminSnapshot.child("password").getValue(String.class);
                        if (passwordFromDB != null && passwordFromDB.equals(hashedInputPassword)) {
                            String nameFromDB = adminSnapshot.child("name").getValue(String.class);
                            String emailFromDB = adminSnapshot.child("email").getValue(String.class);

                            // User is an admin
                            listener.onSuccess(username, nameFromDB, emailFromDB, true);
                            return;
                        }
                    }
                    // Invalid admin credentials
                    listener.onError("Invalid Admin Credentials");
                } else {
                    // User is not an admin, check if the user is a student
                    checkStudent.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot snapshot) {
                            if (snapshot.exists()) {
                                for (DataSnapshot studentSnapshot : snapshot.getChildren()) {
                                    String passwordFromDB = studentSnapshot.child("password").getValue(String.class);
                                    if (passwordFromDB != null && passwordFromDB.equals(hashedInputPassword)) {
                                        String nameFromDB = studentSnapshot.child("name").getValue(String.class);
                                        String emailFromDB = studentSnapshot.child("email").getValue(String.class);

                                        // User is a student
                                        listener.onSuccess(username, nameFromDB, emailFromDB, false);
                                        return;
                                    }
                                }
                                // Invalid student credentials
                                listener.onError("Invalid Student Credentials");
                            } else {
                                // User not found
                                listener.onError("User does not exist");
                            }
                        }

                        @Override
                        public void onCancelled(DatabaseError error) {
                            // Handle onCancelled
                        }
                    });
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Handle onCancelled
            }
        });
    }
}
