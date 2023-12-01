package com.uoft.b07application.ui.student;

public class Complaint {
    private String id;
    private String message;

    public Complaint() {
        // Default constructor required for calls to DataSnapshot.getValue(Complaint.class)
    }

    public Complaint(String id, String message) {
        this.id = id;
        this.message = message;
    }

    public String getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message){
        this.message=message;
    }


    // Setter methods if needed
}

