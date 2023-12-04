package com.uoft.b07application.ui.login;

import androidx.annotation.NonNull;
import com.uoft.b07application.ui.profile.User;
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

        checkAdminUsernameExists(usersRef, username, listener, isAdmin, name, email, password);
    }

    private void checkAdminUsernameExists(DatabaseReference usersRef, String username, OnSignupFinishedListener listener,
                                          boolean isAdmin, String name, String email, String password) {
        usersRef.child("admins").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onError("Username already exists");
                } else {
                    checkStudentUsernameExists(usersRef, username, listener, isAdmin, name, email, password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    private void checkStudentUsernameExists(DatabaseReference usersRef, String username, OnSignupFinishedListener listener,
                                            boolean isAdmin, String name, String email, String password) {
        usersRef.child("students").child(username).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onError("Username already exists");
                } else {
                    checkAdminEmailExists(usersRef, username, listener, isAdmin, name, email, password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    private void checkAdminEmailExists(DatabaseReference usersRef, String username, OnSignupFinishedListener listener,
                                       boolean isAdmin, String name, String email, String password) {
        usersRef.child("admins").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onError("Email already exists");
                } else {
                    checkStudentEmailExists(usersRef, username, listener, isAdmin, name, email, password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    private void checkStudentEmailExists(DatabaseReference usersRef, String username, OnSignupFinishedListener listener,
                                         boolean isAdmin, String name, String email, String password) {
        usersRef.child("students").orderByChild("email").equalTo(email).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    listener.onError("Email already exists");
                } else {
                    createUser(usersRef, username, listener, isAdmin, name, email, password);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });
    }

    private void createUser(DatabaseReference usersRef, String username, OnSignupFinishedListener listener,
                            boolean isAdmin, String name, String email, String password) {
        String hashPassword = PasswordHasher.hashPassword(password);
        DatabaseReference userRef = isAdmin ? usersRef.child("admins").child(username) : usersRef.child("students").child(username);
        User user = isAdmin ? new Admin(name, username, email, hashPassword) : new Student(name, username, email, hashPassword);

        userRef.setValue(user)
                .addOnSuccessListener(aVoid -> {
                    addNewUserToRelations(username, listener);
                    listener.onSuccess();
                })
                .addOnFailureListener(e -> listener.onError(e.getMessage()));
    }

    private void addNewUserToRelations(String username, OnSignupFinishedListener listener){
        final DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference();

        //add relations between events and this new user

        databaseReference.child("events").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot eventSnapshot : dataSnapshot.getChildren()) {
                    DatabaseReference signUpRef = databaseReference.child("signups").push();
                    signUpRef.child("eventKey").setValue(eventSnapshot.getKey());
                    signUpRef.child("username").setValue(username);
                    signUpRef.child("isSignUpEvent").setValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });


        //add relations between announcements and this new user
        databaseReference.child("announcements").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot announcementSnapshot : dataSnapshot.getChildren()) {
                    DatabaseReference readRef = databaseReference.child("reads").push();
                    readRef.child("announcementKey").setValue(announcementSnapshot.getKey());
                    readRef.child("username").setValue(username);
                    readRef.child("isRead").setValue(false);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                listener.onError(databaseError.getMessage());
            }
        });

    }
}


