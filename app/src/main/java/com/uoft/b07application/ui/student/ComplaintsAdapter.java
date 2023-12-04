package com.uoft.b07application.ui.student;

import com.uoft.b07application.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.content.Context;



import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.ArrayList;
import java.util.List;

public class ComplaintsAdapter extends RecyclerView.Adapter<ComplaintsAdapter.ComplaintViewHolder> {

    Context context;
    ArrayList<Complaint> complaintList;

    public ComplaintsAdapter(Context context, ArrayList<Complaint> complaintList) {
        this.context = context;
        this.complaintList = complaintList;
    }

    @NonNull
    @Override
    public ComplaintViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v = LayoutInflater.from(context).inflate(R.layout.student_complaints, parent, false);
        return new ComplaintViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
        Complaint complaint = complaintList.get(position);
        holder.topic.setText(complaint.getId());
        holder.message.setText(complaint.getMessage());
        holder.date.setText(complaint.getDate());
        holder.time.setText(complaint.getTime());
    }

    @Override
    public int getItemCount() {
        return complaintList.size();
    }

    public static class ComplaintViewHolder extends RecyclerView.ViewHolder{
        TextView topic, message, date, time;
        public ComplaintViewHolder(@NonNull View view){
            super(view);
            date=view.findViewById(R.id.date_text_1);
            time=view.findViewById(R.id.time_text_1);
            topic = view.findViewById(R.id.topic);
            message = view.findViewById(R.id.message);
        }
    }

}
