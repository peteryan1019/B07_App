package com.uoft.b07application.ui.student;

public class Complaint {
    private String id;
    private String message;

    String date;
    String time;

    public Complaint() {
        // Default constructor required for calls to DataSnapshot.getValue(Complaint.class)
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Complaint(String id, String message, String date, String time) {
        this.id = id;
        this.message = message;
        this.date=date;
        this.time=time;
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

