package com.uoft.b07application.ui.student;

import android.content.Context;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.uoft.b07application.R;

import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.ui.admin.AnnouncementModel;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>{
    Context context;
    ArrayList<AnnouncementModel> announcementModels;
    String username;

    public AnnouncementAdapter(Context context, ArrayList<AnnouncementModel> announcementModels, String username) {
        this.context = context;
        this.announcementModels = announcementModels;
        this.username = username;
    }

    @NonNull
    @Override
    public AnnouncementAdapter.AnnouncementViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.announcement_item, parent, false);
        return new AnnouncementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnnouncementAdapter.AnnouncementViewHolder holder, int position) {
        AnnouncementModel announcementModel = announcementModels.get(position);
        holder.subject.setText(announcementModel.getSubject());
        holder.message.setText(announcementModel.getMessage());
        holder.date.setText(announcementModel.getDate());
        holder.time.setText(announcementModel.getTime());
        holder.sender_name.setText(announcementModel.getSenderUsername());
        holder.button.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DatabaseReference readsRef = FirebaseDatabase.getInstance().getReference("reads");
                readsRef.orderByChild("announcementKey").equalTo(announcementModel.getAnnouncementKey()).
                        addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot readsSnapshot : snapshot.getChildren()){
                            String targetUsername = (String) readsSnapshot.child("username").getValue();
                            if(targetUsername.equals(username)){
                                boolean isRead = (boolean) readsSnapshot.child("isRead").getValue();
                                if(!isRead){
                                    readsRef.child(readsSnapshot.getKey()).child("isRead").setValue(true);
                                    holder.setViewButtonRead();
                                }
                                else Toast.makeText(context, "Already read", Toast.LENGTH_SHORT).show();
                            }
                        }
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    @Override
    public int getItemCount() {
        return announcementModels.size();
    }


    public static class AnnouncementViewHolder extends RecyclerView.ViewHolder{
        TextView subject, message, date, time, sender_name;
        public Button button;
        public AnnouncementViewHolder(@NonNull View view){
            super(view);
            subject = view.findViewById(R.id.subject);
            date = view.findViewById(R.id.date_text);
            time = view.findViewById(R.id.time_text);
            message = view.findViewById(R.id.message_body);
            sender_name=view.findViewById(R.id.sender_name);

            button = view.findViewById(R.id.unread_button);
//            button.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    button.setEnabled(false);
//                    button.setText("already read");
//                    button.setBackgroundColor(Color.GRAY);
//                }
//            });


        }
        public void setViewButtonRead() {
            button.setEnabled(false);
            button.setText("already read");
            button.setBackgroundColor(Color.GRAY);
        }
    }
}
