package com.uoft.b07application.ui;

import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.uoft.b07application.R;
import com.uoft.b07application.ui.profile.Student;
import com.uoft.b07application.ui.student.StudentActivity;

public class ProfileActivity extends AppCompatActivity {
    String isadminorstudent;
    String old_un;
    String old_em;
    String updated_un;
    String updated_em;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //get lsit of current users on firebase
        reference = FirebaseDatabase.getInstance().getReference("users");

        Intent i = getIntent();
         old_un = i.getStringExtra("username");
        old_em = i.getStringExtra("email");
        isadminorstudent = i.getStringExtra("isadminorstudent");
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_admin_profile);
        //these edit texts only are visible when the user wants to edit the text
        TextView new_username = findViewById(R.id.new_un);
        TextView new_email= findViewById(R.id.new_em);
        Button save_profile = findViewById(R.id.Save);
        new_username.setVisibility(View.INVISIBLE);
        new_email.setVisibility(View.INVISIBLE);
        save_profile.setVisibility(View.INVISIBLE);
        //rest of code
        TextView user_name = (TextView) findViewById(R.id.uns);
        user_name.setText(old_un);
        TextView user_email = (TextView) findViewById(R.id.ems);
        user_email.setText(old_em);
    }

    public void editProfile(View view) {
        Button EditProfileStatus = findViewById(R.id.edit_profile);
        Log.d("ProfileActivity", EditProfileStatus.getText().toString());
        TextView new_username = findViewById(R.id.new_un);
        TextView new_email = findViewById(R.id.new_em);
        TextView old_username = findViewById(R.id.uns);
        TextView old_email = findViewById(R.id.ems);
        Button save_profile = findViewById(R.id.Save);
        if (EditProfileStatus.getText().toString().equals("Edit Profile")) {
            ((TextView) view).setText("Cancel Editing Process");

            //make editing edit texts visible and preexisting username, email invisible
            new_username.setVisibility(View.VISIBLE);
            new_email.setVisibility(View.VISIBLE);
            save_profile.setVisibility(View.VISIBLE);
            old_username.setVisibility(View.INVISIBLE);
            old_email.setVisibility(View.INVISIBLE);
            //
            updated_un = new_username.getText().toString();
            updated_em = new_email.getText().toString();
        }
        else if(EditProfileStatus.getText().toString().equals("Cancel Editing Process")){
            ((TextView) view).setText("Edit Profile");
            new_username.setVisibility(View.INVISIBLE);
            new_email.setVisibility(View.INVISIBLE);
            save_profile.setVisibility(View.INVISIBLE);
            old_username.setVisibility(View.VISIBLE);
            old_email.setVisibility(View.VISIBLE);
        }
    }
    public void Toast(){
        Toast.makeText(this, "Profile Saved!", Toast.LENGTH_LONG).show();
    }
    public void saveProfile(View view){
        Toast();
        Button EditProfileStatus = findViewById(R.id.edit_profile);
        EditProfileStatus.setText("Edit Profile");
        TextView new_username = findViewById(R.id.new_un);
        TextView new_email= findViewById(R.id.new_em);
        TextView old_username = findViewById(R.id.uns);
        TextView old_email= findViewById(R.id.ems);
        Button save_profile = findViewById(R.id.Save);
        //update visibility
        new_username.setVisibility(View.INVISIBLE);
        new_email.setVisibility(View.INVISIBLE);
        old_email.setVisibility(View.VISIBLE);
        old_username.setVisibility(View.VISIBLE);
        view.setVisibility(View.INVISIBLE);



        //update text
        old_username.setText(updated_un);
        old_email.setText(updated_em);
        if(isadminorstudent.equals("Is Admin")){
            reference.child("admins").child(old_un).child("username").setValue(updated_un);
            reference.child("admins").child(old_em).child("email").setValue(updated_em);
        }
        else if(isadminorstudent.equals("Is Student")){
            reference.child("students").child(old_un).child("username").setValue(updated_un);
            reference.child("students").child(old_em).child("email").setValue(updated_em);
        }

    }



}