package com.uoft.b07application.ui.login;

import androidx.annotation.NonNull;
import com.uoft.b07application.ui.profile.Student;
import com.uoft.b07application.ui.profile.Admin;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class SignupActivityModel {
    interface OnSignupFinishedListener {
        void onSuccess();
        void onError(String errorMessage);
    }

    public void registerUser(String name, String email, String username, String password, boolean isAdmin, OnSignupFinishedListener listener) {
        final DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference("users");

        // Check if the username already exists under admins
        usersRef.child("admins").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    // Username already exists as an admin
                    listener.onError("Username already exists");
                } else {
                    // Check if the username exists under students
                    usersRef.child("students").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            if (dataSnapshot.exists()) {
                                // Username already exists as a student
                                listener.onError("Username already exists");
                            } else {
                                // Check if the email exists under admins
                                usersRef.child("admins").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                    @Override
                                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                        if (dataSnapshot.exists()) {
                                            // Email already exists as an admin
                                            listener.onError("Email already exists");
                                        } else {
                                            // Check if the email exists under students
                                            usersRef.child("students").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
                                                @Override
                                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                                    if (dataSnapshot.exists()) {
                                                        // Email already exists as a student
                                                        listener.onError("Email already exists");
                                                    } else {
                                                        // Neither username nor email exists, proceed with signup
                                                        String hashPassword = PasswordHasher.hashPassword(password);
                                                        if (isAdmin) {
                                                            DatabaseReference adminRef = usersRef.child("admins").child(username);
                                                            Admin admin = new Admin(name, username, email, hashPassword);
                                                            adminRef.setValue(admin);
                                                        } else {
                                                            DatabaseReference studentRef = usersRef.child("students").child(username);
                                                            Student student = new Student(name, username, email, hashPassword);
                                                            studentRef.setValue(student);
                                                        }
                                                        listener.onSuccess();
                                                    }
                                                }

                                                @Override
                                                public void onCancelled(@NonNull DatabaseError databaseError) {
                                                    listener.onError(databaseError.getMessage());
                                                }
                                            });
                                        }
                                    }

                                    @Override
                                    public void onCancelled(@NonNull DatabaseError databaseError) {
                                        listener.onError(databaseError.getMessage());
                                    }
                                });
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {
                            listener.onError(databaseError.getMessage());
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }
}


