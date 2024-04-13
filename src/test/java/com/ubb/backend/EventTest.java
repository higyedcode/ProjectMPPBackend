package com.ubb.backend;
import org.junit.jupiter.api.Test;

import com.ubb.backend.model.Event;

import java.time.LocalDate;
import static org.junit.jupiter.api.Assertions.*;

public class EventTest {

    @Test
    public void testGettersAndSetters() {
        // Create an event object
        Event event = new Event("Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street");

        // Test getters
        // assertEquals(1, event.getEventId());
        assertEquals("Birthday Party", event.getEventName());
        assertEquals(LocalDate.of(2024, 4, 15), event.getEventDate());
        assertEquals("123 Main Street", event.getEventLocation());

        // Test setters
        // event.setEventId(2);
        event.setEventName("Wedding");
        event.setEventDate(LocalDate.of(2024, 5, 20));
        event.setEventLocation("456 Elm Street");

        // assertEquals(2, event.getEventId());
        assertEquals("Wedding", event.getEventName());
        assertEquals(LocalDate.of(2024, 5, 20), event.getEventDate());
        assertEquals("456 Elm Street", event.getEventLocation());
    }

    @Test
    public void testEqualsAndHashCode() {
        Event event1 = new Event("Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street");
        Event event2 = new Event("Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street");
        event2.setEventId(event1.getEventId());
        Event event3 = new Event("Wedding", LocalDate.of(2024, 5, 20), "456 Elm Street");

        // Test equals method
        assertTrue(event1.equals(event2)); // event1 should be equal to event2
        assertFalse(event1.equals(event3)); // event1 should not be equal to event3

        // Test hashCode method
        assertEquals(event1.hashCode(), event2.hashCode()); // hashCode of event1 should be equal to hashCode of event2
    }

    @Test
    public void testToString() {
        Event event = new Event( "Birthday Party", LocalDate.of(2024, 4, 15), "123 Main Street");
        String expectedToString = "Event{eventId="+event.getEventId()+", eventName='Birthday Party', eventDate=2024-04-15, eventLocation='123 Main Street'}";
        assertEquals(expectedToString, event.toString());
    }
}
