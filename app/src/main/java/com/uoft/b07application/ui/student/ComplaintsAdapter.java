package com.uoft.b07application.ui.student;

import com.uoft.b07application.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintViewHolder> {

    private final List<Complaint> complaintsList;

    public ComplaintsAdapter(List<Complaint> complaintsList) {
        this.complaintsList = complaintsList;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.complaint_item, parent, false);
        return new ComplaintViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint complaint = complaintsList.get(position);
        holder.bind(complaint);
    }

    @Override
    public int getItemCount() {
        return complaintsList.size();
    }

    static class ComplaintViewHolder extends RecyclerView.ViewHolder {

        private final TextView complaintTextView;

        public ComplaintViewHolder(View itemView) {
            super(itemView);
            complaintTextView = itemView.findViewById(R.id.complaint_text_view);
        }

        public void bind(Complaint complaint) {
            complaintTextView.setText(complaint.getMessage());
        }
    }
}

