package com.uoft.b07application.ui.admin;

import java.util.HashMap;

public class EventModel {
    String eventName;
    String eventDate;

    public EventModel(HashMap<String, String> event) {
        this.eventName = "Event name: " + event.get("eventName");
        this.eventDate = "Date: " + event.get("eventDate");
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }
}
