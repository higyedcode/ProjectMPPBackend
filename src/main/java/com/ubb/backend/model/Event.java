package com.ubb.backend.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.UUID;

public class Event {
    private UUID eventId;
    private String eventName;
    private LocalDate eventDate;
    private String eventLocation;

    public Event(String eventName, LocalDate eventDate, String eventLocation) {
        this.eventId = UUID.randomUUID();
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.eventLocation = eventLocation;
    }

    // Getters
    public UUID getEventId() {
        return eventId;
    }

    public String getEventName() {
        return eventName;
    }

    public LocalDate getEventDate() {
        return eventDate;
    }

    public String getEventLocation() {
        return eventLocation;
    }

    // Setters
    public void setEventId(UUID eventId) {
        this.eventId = eventId;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public void setEventDate(LocalDate eventDate) {
        this.eventDate = eventDate;
    }

    public void setEventLocation(String eventLocation) {
        this.eventLocation = eventLocation;
    }
    public String toString() {
        return "Event{" +
                "eventId=" + eventId +
                ", eventName='" + eventName + '\'' +
                ", eventDate=" + eventDate +
                ", eventLocation='" + eventLocation + '\'' +
                '}';
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Event event = (Event) o;
        return eventId == event.eventId &&
                Objects.equals(eventName, event.eventName) &&
                Objects.equals(eventDate, event.eventDate) &&
                Objects.equals(eventLocation, event.eventLocation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(eventId, eventName, eventDate, eventLocation);
    }
    
}
