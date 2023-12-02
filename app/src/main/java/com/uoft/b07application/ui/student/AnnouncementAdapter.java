package com.uoft.b07application.ui.student;

import android.content.Context;
import com.uoft.b07application.R;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.ui.admin.AnnouncementModel;

import java.util.ArrayList;

public class AnnouncementAdapter extends RecyclerView.Adapter<AnnouncementAdapter.AnnouncementViewHolder>{
    Context context;
    ArrayList<AnnouncementModel> announcementModels;

    public AnnouncementAdapter(Context context, ArrayList<AnnouncementModel> announcementModels) {
        this.context = context;
        this.announcementModels = announcementModels;
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

//        SharedPreferences sharedPreferences = holder.itemView.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//        boolean isDisabled = sharedPreferences.getBoolean("ButtonState_" + position, false);

//        if (isDisabled) {
//            holder.button.setEnabled(false);
//            holder.button.setBackgroundColor(Color.GRAY);
//        } else {
//            holder.button.setEnabled(true);
//            holder.button.setBackgroundResource(R.color.original_background_color); // Assuming you have a drawable for the original background
//        }
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
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    button.setEnabled(false);
                    button.setText("already read");
                    button.setBackgroundColor(Color.GRAY);

//                    SharedPreferences sharedPreferences = v.getContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);
//                    SharedPreferences.Editor editor = sharedPreferences.edit();
//                    editor.putBoolean("ButtonState_" + getBindingAdapterPosition(), true);
//                    editor.apply();
                }
            });


        }

    }
}
