package com.uoft.b07application.ui.student;

import com.uoft.b07application.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;

import java.util.List;

public class ComplaintsAdapter extends FirebaseRecyclerAdapter<Complaint, ComplaintsAdapter.ComplaintViewHolder> {

//    private final List<Complaint> complaintsList;

    public ComplaintsAdapter(
        @NonNull FirebaseRecyclerOptions<Complaint> options)
        {
            super(options);
        }

    // Function to bind the view in Card view(here
    // "person.xml") iwth data in
    // model class(here "person.class")
    @Override
    protected void
    onBindViewHolder(@NonNull ComplaintViewHolder holder,
                     int position, @NonNull Complaint model)
    {

        // Add firstname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.topic.setText(model.getId());

        // Add lastname from model class (here
        // "person.class")to appropriate view in Card
        // view (here "person.xml")
        holder.message.setText(model.getMessage());
    }

    @NonNull
    @Override
    public ComplaintViewHolder
    onCreateViewHolder(@NonNull ViewGroup parent,
                       int viewType)
    {
        View view
                = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.student_complaints, parent, false);
        return new ComplaintViewHolder(view);
    }

    class ComplaintViewHolder
            extends RecyclerView.ViewHolder {
        TextView topic, message;
        public ComplaintViewHolder(@NonNull View itemView)
        {
            super(itemView);

            topic = itemView.findViewById(R.id.topic);
            message = itemView.findViewById(R.id.message);
        }
    }
}
//    public void onBindViewHolder(@NonNull ComplaintViewHolder holder, int position) {
//        Complaint complaint = complaintsList.get(position);
//        holder.bind(complaint);
//    }
//
//
//    public int getItemCount() {
//        return complaintsList.size();
//    }
//
//    static class ComplaintViewHolder extends RecyclerView.ViewHolder {
//
//        private final TextView complaintTextView;
//
//        public ComplaintViewHolder(View itemView) {
//            super(itemView);
//            complaintTextView = itemView.findViewById(R.id.complaint_text_view);
//        }
//
//        public void bind(Complaint complaint) {
//            complaintTextView.setText(complaint.getMessage());
//        }
//    }
//}

