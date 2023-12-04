package com.uoft.b07application.ui.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.uoft.b07application.R;

public class AdminProfileActivity extends AdminActivity {
    String isadminorstudent;
    String old_n;
    String old_em;
    String updated_n;
    String updated_em;
    String username;
    DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
    @Override
    protected int setLayoutId() {
        return R.layout.activity_admin_profile;
    }
    @Override
    protected void setMenu(){
        drawerLayout = findViewById(R.id.admin_profile_drawer_layout);
        navigationView = findViewById(R.id.nav_admin_profile_view);
        toolbar = findViewById(R.id.admin_profile_toolbar);
    }
    @Override
    protected void setButtonListeners(){
        
        //get list of current users on firebase
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        Intent i = getIntent();
        //collect the username and name and store it in a global variable
        old_n = i.getStringExtra("name");
        old_em = i.getStringExtra("email");
        username = i.getStringExtra("username");
        isadminorstudent = i.getStringExtra("isadminorstudent");
        updated_em = old_em;
        updated_n = old_n;
        Log.d("Old_n", "old_n is: "+ old_n);
        Log.d("Old_n", "old_em is: "+ old_em);
        Log.d("username", "username is :" + username);

        //these edit texts only are visible when the user wants to edit the text
        TextView new_username = findViewById(R.id.new_un);
        TextView new_email= findViewById(R.id.new_em);
        Button save_profile = findViewById(R.id.Save);
        new_username.setVisibility(View.INVISIBLE);
        new_email.setVisibility(View.INVISIBLE);
        save_profile.setVisibility(View.INVISIBLE);
        //rest of code
        TextView user_name = (TextView) findViewById(R.id.uns);
        user_name.setText(old_n);
        TextView user_email = (TextView) findViewById(R.id.ems);
        user_email.setText(old_em);
    }
    public void editProfile(View view) {
        Button EditProfileStatus = findViewById(R.id.edit_profile);
        //get references to all buttons and relevant textviews
        TextView new_name = findViewById(R.id.new_un);
        TextView new_email = findViewById(R.id.new_em);
        TextView old_name = findViewById(R.id.uns);
        TextView old_email = findViewById(R.id.ems);
        Button save_profile = findViewById(R.id.Save);

        //open up edit profile page
        if (EditProfileStatus.getText().toString().equals("Edit Profile")) {
            ((TextView) view).setText("Cancel Editing Process");
            new_email.setText(old_em);
            new_name.setText(old_n);
            //make editing edit texts visible and preexisting username, email invisible
            new_name.setVisibility(View.VISIBLE);
            new_email.setVisibility(View.VISIBLE);
            save_profile.setVisibility(View.VISIBLE);
            old_name.setVisibility(View.INVISIBLE);
            old_email.setVisibility(View.INVISIBLE);
        }
        //go back to original page
        else if(EditProfileStatus.getText().toString().equals("Cancel Editing Process")){
            ((TextView) view).setText("Edit Profile");
            new_name.setVisibility(View.INVISIBLE);
            new_email.setVisibility(View.INVISIBLE);
            save_profile.setVisibility(View.INVISIBLE);
            old_name.setVisibility(View.VISIBLE);
            old_email.setVisibility(View.VISIBLE);
            //reset the updated un and email.
        }
    }

    public void saveProfile(View view){

        Button EditProfileStatus = findViewById(R.id.edit_profile);
        EditProfileStatus.setText("Edit Profile");
        Toast.makeText(this, "Profile Saved!", Toast.LENGTH_LONG).show();
        //get references
        TextView new_name = findViewById(R.id.new_un);
        TextView new_email= findViewById(R.id.new_em);
        TextView old_name = findViewById(R.id.uns);
        TextView old_email= findViewById(R.id.ems);
        Button save_profile = findViewById(R.id.Save);
        Log.d("username", "username is " + username);
        //collect the updated text
        updated_n = new_name.getText().toString();
        updated_em = new_email.getText().toString().toLowerCase();
        if(updated_n.equals("") || updated_em.equals("")){
            Toast.makeText(this, "you cannot have an empty string as username or email!", Toast.LENGTH_LONG).show();
        }
        else{
            Log.d("updated un", "updated un is: "+ updated_n);
            Log.d("updated un", "updated em is: "+ updated_em);
            //update the display of the old username
            old_name.setText(updated_n);
            old_email.setText(updated_em);
            //update visibility
            new_name.setVisibility(View.INVISIBLE);
            new_email.setVisibility(View.INVISIBLE);
            old_email.setVisibility(View.VISIBLE);
            old_name.setVisibility(View.VISIBLE);
            view.setVisibility(View.INVISIBLE);

            //update text

            try {
                if (isadminorstudent.equals("Is Admin")) {
                    reference.child("admins").child(username).child("name").setValue(updated_n);
                    reference.child("admins").child(username).child("email").setValue(updated_em);
                } else if (isadminorstudent.equals("Is Student")) {
                    reference.child("students").child(username).child("name").setValue(updated_n);
                    reference.child("students").child(username).child("email").setValue(updated_em);
                }
            } catch (Exception e) {
                e.printStackTrace();
                Log.d("message", "something failed here");
                // Handle the exception appropriately, log it, or show an error message.
            }
            Log.d("old_n", "old_name is: "+ old_n);
            Log.d("old_em", "old_em is: "+ old_em);
            old_n = updated_n;
            old_em = updated_em;
        }


    }
}