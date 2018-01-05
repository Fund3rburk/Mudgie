package com.mecklenburgsolutions.mudgie;

import java.time.LocalDateTime;

/**
 * Created by tfunderburk on 12/30/17.
 */

public class CalendarEvent {
    int id;
    String eventDescription;
    LocalDateTime eventDateTime;

    public CalendarEvent(){
     //used for persistence
    }

    public CalendarEvent(int id, String eventText, LocalDateTime eventDateTime){
        this.id = id;
        this.eventDescription = eventText;
        this.eventDateTime = eventDateTime;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getEventDescription() {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription) {
        this.eventDescription = eventDescription;
    }

    public LocalDateTime getEventDateTime() {
        return eventDateTime;
    }

    public void setEventDateTime(LocalDateTime eventDateTime) {
        this.eventDateTime = eventDateTime;
    }
}
