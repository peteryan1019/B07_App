package com.uoft.b07application.ui.admin;

import java.util.HashMap;

public class AnnouncementModel {
    String senderUsername;
    String senderEmail;
    String recipient;
    String subject;
    String message;

    String date;
    String time;

    String announcementKey;

    public AnnouncementModel() {

    }

    public AnnouncementModel(String senderUsername, String senderEmail
            , String recipient, String subject, String message, String date, String time, String announcementKey) {

        this.senderUsername = senderUsername;
        this.senderEmail = senderEmail;
        this.recipient = "TO: " + recipient;
        this.subject = "Subject: " + subject;
        this.message = message;
        this.date = date;
        this.time = time;


        this.announcementKey = announcementKey;

    }

    public AnnouncementModel(HashMap<String, String> hashMap) {
        this.senderUsername = hashMap.get("senderUsername");
        this.senderEmail = hashMap.get("senderEmail");
        this.recipient = "TO: " + hashMap.get("recipient");
        this.subject = "Subject: " + hashMap.get("subject");
        this.message = hashMap.get("message");
        this.date = hashMap.get("date");

        this.time = hashMap.get("time");
        this.announcementKey = hashMap.get("announcementKey");
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) {
        this.time = time;

    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public String getRecipient() {
        return recipient;
    }

    public String getSubject() {
        return subject;
    }

    public String getMessage() {
        return message;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public String getAnnouncementKey() {
        return announcementKey;
    }

}
