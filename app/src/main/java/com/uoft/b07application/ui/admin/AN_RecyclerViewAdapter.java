package com.uoft.b07application.ui.admin;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uoft.b07application.R;

import java.util.ArrayList;

public class AN_RecyclerViewAdapter extends RecyclerView.Adapter<AN_RecyclerViewAdapter.AN_ViewHolder> {
    Context context;
    ArrayList<AnnouncementModel> announcementModels;
    public AN_RecyclerViewAdapter(Context context, ArrayList<AnnouncementModel> announcementModels){
        this.context = context;
        this.announcementModels = announcementModels;
    }
    @NonNull
    @Override
    public AN_RecyclerViewAdapter.AN_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.announcement_recycler_row, parent, false);

        return new AN_RecyclerViewAdapter.AN_ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AN_RecyclerViewAdapter.AN_ViewHolder holder, int position) {
        holder.senderUsernameView.setText(announcementModels.get(position).getSenderUsername());
        holder.senderEmailView.setText(announcementModels.get(position).getSenderEmail());
        holder.recipientView.setText(announcementModels.get(position).getRecipient());
        holder.subjectView.setText(announcementModels.get(position).getSubject());
        holder.messageBodyView.setText(announcementModels.get(position).getMessage());
    }

    @Override
    public int getItemCount() {
        return announcementModels.size();
    }

    public static class AN_ViewHolder extends RecyclerView.ViewHolder {
        TextView senderUsernameView;
        TextView senderEmailView;
        TextView messageBodyView;
        TextView recipientView;
        TextView subjectView;

        public AN_ViewHolder(@NonNull View itemView) {
            super(itemView);
            senderUsernameView = itemView.findViewById(R.id.recycler_senderUsername);
            senderEmailView = itemView.findViewById(R.id.recycler_senderEmail);
            messageBodyView = itemView.findViewById(R.id.recycler_message);
            recipientView = itemView.findViewById(R.id.recycler_recipient);
            subjectView = itemView.findViewById(R.id.recycler_subject);
        }
    }
}
