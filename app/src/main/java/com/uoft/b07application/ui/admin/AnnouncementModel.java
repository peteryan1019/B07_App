package com.uoft.b07application.ui.admin;

import java.util.HashMap;

public class AnnouncementModel {
    String senderUsername;
    String senderEmail;
    String recipient;
    String subject;
    String message;

    public AnnouncementModel(String senderUsername, String senderEmail
            , String recipient, String subject, String message) {
        this.senderUsername = senderUsername;
        this.senderEmail = senderEmail;
        this.recipient = "TO: " + recipient;
        this.subject = "Subject: " + subject;
        this.message = message;
    }

    public AnnouncementModel(HashMap<String, String> hashMap) {
        this.senderUsername = hashMap.get("senderUsername");
        this.senderEmail = hashMap.get("senderEmail");
        this.recipient = "TO: " + hashMap.get("recipient");
        this.subject = "Subject: " + hashMap.get("subject");
        this.message = hashMap.get("message");
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
}
